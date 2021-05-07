package com.it.edu.map.Test;

/**
 * @author     ：
 * @date       ：Created in 2019/5/26 17:02
 * @description：天下风云出我辈，一入代码岁月催
 * @version:  V1.0
 */
public class Keys {

    public static final int MAX_KEY = 10000000;

    private static final Key[] KEYS_CACHE = new Key[MAX_KEY];

    static {
        for (int i = 0; i < MAX_KEY; ++i) {
            KEYS_CACHE[i] = new Key(i);
        }
    }

    public static Key hash(int value) {
        return KEYS_CACHE[value];
    }
}