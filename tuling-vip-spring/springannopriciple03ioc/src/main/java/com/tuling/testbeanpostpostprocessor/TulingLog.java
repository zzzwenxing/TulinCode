package com.tuling.testbeanpostpostprocessor;

import org.springframework.stereotype.Component;

/**
 * Created by smlz on 2019/5/31.
 */
@Component
public class TulingLog {

    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void print() {
        if(flag) {
            System.out.println("TulingLog.print()打印的的flag:"+flag);
        }else{
            System.out.println("TulingLog.print()打印的的flag:"+false);
        }

    }
}
