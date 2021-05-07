package com.it.edu.map;


import sun.misc.VM;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：图灵-杨过
 * @date：2019/7/17
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class MapTest {


    public static void main(String[] args) {
        MapResizer map = new MapResizer();
        for (int i=0;i<30;i++){
            new Thread(new MapResizer()).start();
        }

    }
}
