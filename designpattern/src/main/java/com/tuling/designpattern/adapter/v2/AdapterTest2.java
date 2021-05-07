package com.tuling.designpattern.adapter.v2;

/**
 * @author 腾讯课堂-图灵学院  郭嘉
 * @Slogan 致敬大师，致敬未来的你
 */
public class AdapterTest2 {
    public static void main(String[] args) {
        Adpater adpater=new Adpater();
        adpater.output5v();

    }
}

class Adaptee{
    public int output220v(){
        return 220;
    }
}
interface Target {
    int output5v();
}
// Class Adapter
class Adpater extends Adaptee implements Target{

    @Override
    public int output5v() {
        int i= output220v();
        //  ......
        System.out.println(String.format( "原始电压： %d v  - >  输出电压： %d  v  ",i,5 ));
        return 5;
    }
}