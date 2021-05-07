package com.tuling;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * Created by smlz on 2019/5/26.
 */

public class TulingLog {

    @Value("#{1*2}")
    private int flag;

    @Value("2019-05-29")
    private Date date;

    @Override
    public String toString() {
        return "TulingLog{" +
                "flag=" + flag +
                '}';
    }

    public TulingLog() {

        System.out.println("我是TulingLog的构造方法");
    }



    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
