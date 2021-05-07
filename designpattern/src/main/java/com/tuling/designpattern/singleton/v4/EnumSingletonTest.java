package com.tuling.designpattern.singleton.v4;

import java.io.*;

/**
 * @author 腾讯课堂-图灵学院  郭嘉
 * @Slogan 致敬大师，致敬未来的你
 */
public class EnumSingletonTest {
    public static void main(String[] args) throws IOException {
        EnumSingleton instance=EnumSingleton.INSTANCE;
//        EnumSingleton instance1=EnumSingleton.INSTANCE;
//        System.out.println(instance==instance1);

//        try(ObjectOutputStream oos=new ObjectOutputStream( new FileOutputStream( "EnumSingleton" ) )) {
//            oos.writeObject( instance );
//        }

        try (ObjectInputStream ois=new ObjectInputStream( new FileInputStream( "EnumSingleton" ) )){
            EnumSingleton object=((EnumSingleton) ois.readObject());
            System.out.println(object==instance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}

enum EnumSingleton{
    INSTANCE;

    public void print(){
        System.out.println(hashCode());
    }

}
