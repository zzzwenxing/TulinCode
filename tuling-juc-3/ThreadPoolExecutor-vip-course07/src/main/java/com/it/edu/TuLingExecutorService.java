package com.it.edu;

/**
 * @author ：图灵-杨过
 * @date：2019/7/22
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public interface TuLingExecutorService {

    /**
     * 执行任务
     * @param task
     */
    public void execute(Runnable task);

    public void shutdown();

    public int getCorePoolSize();

    public Runnable getTask();

}
