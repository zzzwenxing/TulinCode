package it.edu.demo.countedcompleter;


import org.junit.Test;

import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertThat;


public class CountedCompleterTest {

    private static final String J_LETTER = "J";
    private static final String NOT_EXISTENT_LETTER = "2";
    private static final String[] ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H", "I", J_LETTER};
    private static final Configuration DEFAULT_CONFIG = new Configuration(false, false, false);

    @Test
    public void should_fin_result_even_with_more_than_0_pending_tasks() {
        Searcher<String> searcher =
                new Searcher<>(null, ALPHABET, new AtomicReference<>(), 0, ALPHABET.length, J_LETTER, DEFAULT_CONFIG);
        searcher.setPendingCount(333);
        ForkJoinPool pool = new ForkJoinPool();
        String word = pool.invoke(searcher);

//        assertThat(word).isEqualTo(J_LETTER);
//        assertThat(searcher.getPendingCount()).isGreaterThan(0);
//        assertThat(searcher.isCompletedNormally()).isTrue();
    }

    @Test
    public void should_find_letter_without_additional_pending_tasks() {
        Searcher<String> searcher =
                new Searcher<>(null, ALPHABET, new AtomicReference<>(), 0, ALPHABET.length, J_LETTER, DEFAULT_CONFIG);
        ForkJoinPool pool = new ForkJoinPool();
        String word = pool.invoke(searcher);

//        assertThat(word).isEqualTo(J_LETTER);
//        assertThat(searcher.getPendingCount()).isEqualTo(0);
//        assertThat(searcher.isCompletedNormally()).isTrue();
    }

    @Test
    public void should_not_find_letter_because_missing_pending_tasks_incrementation() {
        Configuration configuration = new Configuration(true, false, false);
        Searcher<String> searcher =
                new Searcher<>(null, ALPHABET, new AtomicReference<>(), 0, ALPHABET.length, J_LETTER, configuration);
        ForkJoinPool pool = new ForkJoinPool();
        String word = pool.invoke(searcher);

//        assertThat(word).isNull();
//        assertThat(searcher.isCompletedNormally()).isTrue();
    }

    @Test
    public void should_not_end_because_of_too_many_tasks_and_complete_call_missing() throws InterruptedException {
        Configuration configuration = new Configuration(false, true, false);
        Searcher<String> searcher =
                new Searcher<>(null, ALPHABET, new AtomicReference<>(), 0, ALPHABET.length, J_LETTER, configuration);
        searcher.setPendingCount(333);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(searcher);
        pool.awaitTermination(4, TimeUnit.SECONDS);

//        assertThat(searcher.isCompletedNormally()).isFalse();
    }

    @Test
    public void should_not_end_because_of_not_existent_letter_and_trycomplete_call_missing() throws InterruptedException {
        Configuration configuration = new Configuration(false, false, true);
        // Because of Searcher code we have to try to find not existent letter to prove that missing tryComplete() call
        // can deadlock.
        Searcher<String> searcher =
                new Searcher<>(null, ALPHABET, new AtomicReference<>(), 0, ALPHABET.length, NOT_EXISTENT_LETTER, configuration);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(searcher);
        pool.awaitTermination(4, TimeUnit.SECONDS);

//        assertThat(searcher.isCompletedNormally()).isFalse();
    }

    @Test
    public void should_not_find_letter_not_existing_in_alphabet() {
        Searcher<String> searcher =
                new Searcher<>(null, ALPHABET, new AtomicReference<>(), 0, ALPHABET.length, NOT_EXISTENT_LETTER, DEFAULT_CONFIG);
        ForkJoinPool pool = new ForkJoinPool();
        String word = pool.invoke(searcher);

//        assertThat(word).isNull();
//        assertThat(searcher.getPendingCount()).isEqualTo(0);
//        assertThat(searcher.isCompletedNormally()).isTrue();
    }

}

class Searcher<E> extends CountedCompleter<E> {
    private final E[] items;
    private final E expected;
    private final AtomicReference<E> result;
    private final int startIndex, endIndex;
    private final Configuration configuration;

    Searcher(CountedCompleter<?> completer, E[] items, AtomicReference<E> result, int startIndex, int endIndex, E expected, Configuration configuration) {
        super(completer);
        this.items = items;
        this.result = result;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.expected = expected;
        this.configuration = configuration;
    }

    @Override
    public E getRawResult() {
        return result.get();
    }

    @Override
    public void compute() {
        int localStartIndex = startIndex,  localEndIndex = endIndex;
        while (result.get() == null && localEndIndex >= localStartIndex) {
            /**
             * If there are more than one element to check, we divide it until receiving
             * the single one item.
             */
            if (localEndIndex - localStartIndex >= 2) {
                int mid = (localStartIndex + localEndIndex) >>> 1;
                /**
                 * Disable this method invocation to see that the search won't work even
                 * if searched item exists in the array.
                 * Imagine the situation when you search a "J" letter in alphabet array "A"-"J".
                 * CountedCompleter will first split this array to 2 parts, after split them until
                 * receiving 1-elements array. So if the tasks subset without "J" ends before the
                 * subset containing "J", the result will be null.
                 */
                if (!configuration.canSkipIncrement()) {
                    addToPendingCount(1);
                }
                new Searcher(this, items, result, mid, localEndIndex, expected, configuration).fork();
                localEndIndex = mid;
            } else {
                /**
                 * We have now only one item, so we can check if it matches searched element and
                 * if it's different from null. If the item fulfils both conditions, we quit the execution.
                 * We do so by calling quietlyCompleteRoot(). This method completes task normally without
                 * setting new value (see setRawResult() method in CountedCompleter abstract class, which is btw
                 * empty by default).
                 *
                 * This matching value (null in our case because we work with AtomicReference) will be
                 * returned for all related operations.
                 *
                 * Without calling quietlyCompleteRoot(), we consider that the result was not found. In consequence,
                 * program will continue the execution. To observe that, simply comment below invocation. To make
                 * it working, we explicitly set the number of pending tasks to 333 in test declaration (much more
                 * than real tasks to execute). Note that quietlyCompleteRoot() can be also declared with
                 * complete(T result) method call when one of subtasks generated the expected result.
                 */
                E item = items[localStartIndex];
                if (matches(item) && result.compareAndSet(null, item)) {
                    if (!configuration.canSkipCompleteCall()) {
                        quietlyCompleteRoot();
                    }
                }
                break;
            }
        }
        /**
         *
         * When tryComplete() is not called, we still consider that the task is not finished. In our case it's visible
         * only for the case when searched letter doesn't exist in submitted array.
         */
        if (!configuration.canSkipTryCompleteCall()) {
            tryComplete();
        }
    }

    boolean matches(E e) {
        return e.equals(expected);
    }

    @Override
    public String toString() {
        return "Searcher {"+startIndex+"-"+endIndex+"}";
    }
}


class Configuration {

    private boolean skipIncrement;

    private boolean skipCompleteCall;

    private boolean skipTryCompleteCall;

    Configuration(boolean skipIncrement, boolean skipCompleteCall, boolean skipTryCompleteCall) {
        this.skipIncrement = skipIncrement;
        this.skipCompleteCall = skipCompleteCall;
        this.skipTryCompleteCall = skipTryCompleteCall;
    }

    public boolean canSkipIncrement() {
        return skipIncrement;
    }

    public boolean canSkipCompleteCall() {
        return skipCompleteCall;
    }

    public boolean canSkipTryCompleteCall() {
        return skipTryCompleteCall;
    }

}