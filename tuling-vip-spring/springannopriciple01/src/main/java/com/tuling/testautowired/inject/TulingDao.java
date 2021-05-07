package com.tuling.testautowired.inject;

import org.springframework.stereotype.Repository;

/**
 * Created by smlz on 2019/5/24.
 */
@Repository
public class TulingDao {

    private int flag=1;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "TulingDao{" +
                "flag=" + flag +
                '}';
    }
}
