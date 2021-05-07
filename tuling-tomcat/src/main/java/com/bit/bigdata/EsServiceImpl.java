package com.bit.bigdata;

import java.util.logging.Logger;

public class EsServiceImpl {
    Logger logger = Logger.getLogger("EsServiceImpl");
    private volatile long count = 0;
    private volatile long fails = 0;
    private volatile long succeeds = 0;


    public void putData(String nodeJson) {
        try {
			
            succeeds++;
        } catch (Exception e) {
            e.printStackTrace();
            fails++;
        } finally {
            count++;
        }
    }

    public long getCount() {
        return count;
    }

    public long getFails() {
        return fails;
    }

    public long getSucceeds() {
        return succeeds;
    }


}
