package com.tuling.designpattern.factorymethod;

/**
 * @author 腾讯课堂-图灵学院  郭嘉
 * @Slogan 致敬大师，致敬未来的你
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        Application application= new ConcreteProductB();
        Product   product=application.getObject();
        product.method1();

    }
}

interface Product{
    void method1();
}

class ProductA implements Product{

    @Override
    public void method1() {
        System.out.println( "ProductA.method1 executed." );
    }
}

class ProductB implements Product{

    @Override
    public void method1() {
        System.out.println( "ProductB.method1 executed." );
    }
}


// 简单工厂
class SimpleFactory{
    public static Product createProduct(String type){
        if ("A".equals( type )){
            return new ProductA();
        }

        return null;
    }
}

//  变化 ， 共同点
abstract class Application {

    // 工厂方法
    public abstract Product createProduct();

    public Product getObject() {

        Product  product=createProduct();
        // ......
        return product;
    }
}

class ConcreteProductA extends Application{

    @Override
    public Product createProduct() {
        ProductA productA=new ProductA();
        // ...
        return productA;
    }
}


class ConcreteProductB extends Application{

    @Override
    public Product createProduct() {
        ProductB productB=new ProductB();
        return productB;
    }
}
