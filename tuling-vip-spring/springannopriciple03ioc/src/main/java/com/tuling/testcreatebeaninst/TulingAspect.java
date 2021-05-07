package com.tuling.testcreatebeaninst;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by smlz on 2019/6/3.
 */
public class TulingAspect {

    private TulingLog tulingLog;



    public TulingLog getTulingLog() {
        return tulingLog;
    }

    public void setTulingLog(TulingLog tulingLog) {
        this.tulingLog = tulingLog;
    }

    @Override
    public String toString() {
        return "TulingAspect{" +
                "tulingLog=" + tulingLog +
                '}';
    }
}
