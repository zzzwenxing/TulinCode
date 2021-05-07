package it.edu.demo.recursivetask;

import java.util.concurrent.RecursiveTask;
/**
 * RecursiveTask 并行计算，同步有返回值
 * ForkJoin框架处理的任务基本都能使用递归处理，比如求斐波那契数列等，但递归算法的缺陷是：
 *    一只会只用单线程处理，
 *    二是递归次数过多时会导致堆栈溢出；
 * ForkJoin解决了这两个问题，使用多线程并发处理，充分利用计算资源来提高效率，同时避免堆栈溢出发生。
 * 当然像求斐波那契数列这种小问题直接使用线性算法搞定可能更简单，实际应用中完全没必要使用ForkJoin框架，
 * 所以ForkJoin是核弹，是用来对付大家伙的，比如超大数组排序。
 * 最佳应用场景：多核、多内存、可以分割计算再合并的计算密集型任务
 */
class LongSum extends RecursiveTask<Long> {

    static final int SEQUENTIAL_THRESHOLD = 1000;
    static final long NPS = (1000L * 1000 * 1000);
    static final boolean extraWork = true; // change to add more than just a sum


    int low;
    int high;
    int[] array;

    LongSum(int[] arr, int lo, int hi) {
        array = arr;
        low = lo;
        high = hi;
    }

    /**
     * fork()方法：将任务放入队列并安排异步执行，一个任务应该只调用一次fork()函数，除非已经执行完毕并重新初始化。
     * tryUnfork()方法：尝试把任务从队列中拿出单独处理，但不一定成功。
     * join()方法：等待计算完成并返回计算结果。
     * isCompletedAbnormally()方法：用于判断任务计算是否发生异常。
     */
    protected Long compute() {

        if (high - low <= SEQUENTIAL_THRESHOLD) {
            long sum = 0;
            for (int i = low; i < high; ++i) {
                sum += array[i];
            }
            return sum;

        } else {
            int mid = low + (high - low) / 2;
            LongSum left = new LongSum(array, low, mid);
            LongSum right = new LongSum(array, mid, high);
            left.fork();
            right.fork();
            long rightAns = right.join();
            long leftAns = left.join();
            return leftAns + rightAns;
        }
    }
}

       