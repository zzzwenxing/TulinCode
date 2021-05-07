package com.tuling.testautowired.resource;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * Created by smlz on 2019/5/24.
 */
@Service
public class TulingService {


    /**
     * @resource 是不支持@Primary的
     */
    @Resource
    private TulingDao tulingDao;

    @Override
    public String toString() {
        return "TulingService{" +
                "tulingDao=" + tulingDao +
                '}';
    }

    public TulingDao getTulingDao() {
        return tulingDao;
    }

    public void setTulingDao(TulingDao tulingDao) {
        this.tulingDao = tulingDao;
    }
}
