package com.tuling.testspringiocstarter;

import org.springframework.stereotype.Repository;

/**
 * Created by smlz on 2019/5/19.
 */
@Repository
//@Scope(value = "prototype")
public class TulingDao {

    private TulingDataSource tulingDataSource;


    public TulingDao( TulingDataSource tulingDataSource) {
        this.tulingDataSource = tulingDataSource;
        System.out.println("本类的DataSource"+this.tulingDataSource);
    }

}
