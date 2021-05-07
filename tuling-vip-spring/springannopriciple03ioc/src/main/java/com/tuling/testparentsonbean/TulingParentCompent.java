package com.tuling.testparentsonbean;



/**
 * Created by smlz on 2019/6/3.
 */
public abstract class TulingParentCompent {

    private TulingCompent tulingCompent;

    public TulingCompent getTulingCompent() {
        return tulingCompent;
    }

    public void setTulingCompent(TulingCompent tulingCompent) {
        this.tulingCompent = tulingCompent;
    }
}
