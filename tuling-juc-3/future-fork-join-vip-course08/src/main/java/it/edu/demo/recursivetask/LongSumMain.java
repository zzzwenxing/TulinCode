package it.edu.demo.recursivetask;

import it.edu.demo.utils.Utils;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class LongSumMain {
	//获取逻辑处理器数量
	static final int NCPU = Runtime.getRuntime().availableProcessors();
	/** for time conversion */
	static final long NPS = (1000L * 1000 * 1000);

	static long calcSum;

	static final boolean reportSteals = true;

	public static void main(String[] args) throws Exception {
		int[] array = Utils.buildRandomIntArray(20000000);
		System.out.println("cpu-num:"+NCPU);
		//单线程下计算数组数据总和
 		calcSum = seqSum(array);
		System.out.println("seq sum=" + calcSum);

		//采用fork/join方式将数组求和任务进行拆分执行，最后合并结果
		LongSum ls = new LongSum(array, 0, array.length);
  		ForkJoinPool fjp  = new ForkJoinPool(NCPU); //使用的线程数
		ForkJoinTask<Long> task = fjp.submit(ls);
		System.out.println("forkjoin sum=" + task.get());

		if(task.isCompletedAbnormally()){
			System.out.println(task.getException());
		}

		fjp.shutdown();

	}


	static long seqSum(int[] array) {
		long sum = 0;
		for (int i = 0; i < array.length; ++i)
			sum += array[i];
		return sum;
	}
}