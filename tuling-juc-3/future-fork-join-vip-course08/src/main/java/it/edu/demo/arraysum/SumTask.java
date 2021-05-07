package it.edu.demo.arraysum;

import it.edu.demo.utils.SumUtils;

import java.util.concurrent.Callable;

public class SumTask implements Callable<Long> {
    int lo;
    int hi;
    int[] arr;

    public SumTask(int[] a, int l, int h) {
        lo = l;
        hi = h;
        arr = a;
    }

    public Long call() { //override must have this type
        //System.out.printf("The range is [%d - %d]\n", lo, hi);
        long result = SumUtils.sumRange(arr, lo, hi);
        return result;
    }
}
