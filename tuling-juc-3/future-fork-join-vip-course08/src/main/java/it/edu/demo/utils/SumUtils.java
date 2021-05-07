package it.edu.demo.utils;

public class SumUtils {

    public static long sumRange(int[] arr, int lo, int hi) {
        long result = 0;

        for (int j = lo; j < hi; j++)
            result += arr[j];
        return result;
    }

}
