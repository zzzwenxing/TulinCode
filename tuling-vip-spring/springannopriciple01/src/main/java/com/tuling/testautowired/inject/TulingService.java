package com.tuling.testautowired.inject;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by smlz on 2019/5/24.
 */
@Service
public class TulingService {


    @Inject
    private TulingDao tulingDao;

    @Override
    public String toString() {
        return "TulingService{" +
                "tulingDao=" + tulingDao +
                '}';
    }

    public TulingDao getTulingDao2() {
        return tulingDao;
    }

    public void setTulingDao(TulingDao tulingDao) {
        this.tulingDao = tulingDao;
    }
}
