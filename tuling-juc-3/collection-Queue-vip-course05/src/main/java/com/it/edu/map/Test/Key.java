package com.it.edu.map.Test;

/**
 * @author     ：
 * @date       ：Created in 2019/5/26 17:01
 * @description：天下风云出我辈，一入代码岁月催
 * @modified By：
 * @version:  V1.0
 */
public class Key {

    private final int value;

    Key(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Key key = (Key) o;
        return value == key.value;
    }

    @Override
    public int hashCode() {
        return this.value;
    }

    /*@Override
    public int compareTo(Key o) {
        int x = this.value , y = o.value;
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }*/
}