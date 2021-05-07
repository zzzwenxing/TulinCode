package com.tuling.designpattern.templatemethod;

/**
 * @author 腾讯课堂-图灵学院  郭嘉
 * @Slogan 致敬大师，致敬未来的你
 */
public class TemplateMethodTest {
    public static void main(String[] args) {
        AbstractClass abstractClass=new SubClass1();
        abstractClass.operation();
    }
}


abstract class AbstractClass{

    public void operation(){
        // open
        System.out.println(" pre ... ");

        System.out.println(" step1 ");

        System.out.println(" step2 ");

        templateMethod();
        // ....


    }
    abstract protected  void  templateMethod();

}

class SubClass extends AbstractClass{

    @Override
    protected void templateMethod() {
        System.out.println("SubClass executed .  ");
    }
}

class SubClass1 extends AbstractClass{

    @Override
    protected void templateMethod() {
        System.out.println("SubClass1 executed .  ");
    }
}