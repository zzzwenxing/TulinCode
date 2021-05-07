package com.it.edu.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DumpMultiThread {

    public static void main(String[] args){
        /*
         *获取Java线程管理Bean
         */
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //仅仅获取线程与线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        //打印线程信息
        for (ThreadInfo threadInfo:threadInfos){
            System.out.println("thread ID:--->"+threadInfo.getThreadId()
                                + ":thread Name:--->"+threadInfo.getThreadName());
        }
    }

}
