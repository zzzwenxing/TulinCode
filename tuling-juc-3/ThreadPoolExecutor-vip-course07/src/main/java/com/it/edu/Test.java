package com.it.edu;

import com.it.edu.policy.DefaultPolicyHandler;

/**
 * @author ：图灵-杨过
 * @date：2019/7/25
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class Test {

    public static void main(String[] args) {
        TulingThreadPoolExecutor pool = new TulingThreadPoolExecutor(3,3,60,new DefaultPolicyHandler());
        for (int i=0;i<10;i++){
            pool.execute(new Task(i));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }

}
