package com.tuling.designpattern.singleton.v3;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 腾讯课堂-图灵学院  郭嘉
 * @Slogan 致敬大师，致敬未来的你
 */
public class InnerClassSingletonTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        //        InnerClassSingleton instance=InnerClassSingleton.getInstance();
        //        InnerClassSingleton instance1=InnerClassSingleton.getInstance();
        //
        //        System.out.println(instance==instance1);
        // System.out.println(InnerClassSingleton.name);


//        Constructor<InnerClassSingleton> declaredConstructor=InnerClassSingleton.class.getDeclaredConstructor();
//        declaredConstructor.setAccessible( true );
//        InnerClassSingleton innerClassSingleton=declaredConstructor.newInstance();
//        InnerClassSingleton instance=InnerClassSingleton.getInstance();
//
//        System.out.println(innerClassSingleton==instance);

        InnerClassSingleton instance=InnerClassSingleton.getInstance();
//        try(ObjectOutputStream oos=new ObjectOutputStream( new FileOutputStream( "instance" ) )) {
//            oos.writeObject( instance );
//        }

        try (ObjectInputStream ois=new ObjectInputStream( new FileInputStream( "instance" ) )) {
            InnerClassSingleton innerClassSingleton=(InnerClassSingleton) ois.readObject();

            System.out.println( innerClassSingleton == instance );

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}

class InnerClassSingleton implements Serializable {
    // private static final long serialVersionUID = 6922639953390195232L;
    // private  static final long serialVersionUID = 42L;
    public static String name="yyy";
    public static String name1="yyy";
    public static String name2="yyy";

    static {
        System.out.println( " InnerClassSingleton " ); //  1
    }

    private InnerClassSingleton() {

        if (SingletonHolder.instance != null) {
            throw new RuntimeException( " 不允许多个实例。" );
        }

    }

    public static InnerClassSingleton getInstance() {
        return SingletonHolder.instance;
    }

    Object readResolve() throws ObjectStreamException {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static InnerClassSingleton instance=new InnerClassSingleton();

        static {
            System.out.println( " SingletonHolder " );// 2
        }
    }

}
