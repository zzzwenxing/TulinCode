package com.tuling.designpattern.singleton.v2;

/**
 * @author 腾讯课堂-图灵学院  郭嘉
 * @Slogan 致敬大师，致敬未来的你
 */
public class HungrySingletonTest {
    public static void main(String[] args) {
        HungrySingleton instance=HungrySingleton.getInstance();
        HungrySingleton instance1=HungrySingleton.getInstance();
        System.out.println(instance==instance1);
    }
}
class HungrySingleton{

    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){

    }
    public static HungrySingleton getInstance() {
        return instance;
    }
}
