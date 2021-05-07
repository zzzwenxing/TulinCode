package com.tuling.testautowired.autowired;


/**
 * Created by smlz on 2019/5/24.
 */
public class TulingService {

    private TulingDao tulingDao;

    public TulingDao getTulingDao() {
        return tulingDao;
    }

    public void setTulingDao(TulingDao tulingDao) {
        this.tulingDao = tulingDao;
    }

    @Override
    public String toString() {
        return "TulingService{" +
                "tulingDao=" + tulingDao +
                '}';
    }


}
