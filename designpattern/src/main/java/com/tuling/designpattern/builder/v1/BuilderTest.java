package com.tuling.designpattern.builder.v1;

/**
 * @author 腾讯课堂-图灵学院  郭嘉
 * @Slogan 致敬大师，致敬未来的你
 */
public class BuilderTest {
    public static void main(String[] args) {

           ProductBuilder productBuilder=new DefaultProductBuilder();

           Director director=new Director( productBuilder );
           Product product=director.makeProduct( "part1", "part2", "part3", "part4" );
           System.out.println(product);


    }
}

interface ProductBuilder{

    void buildPart1(String part1 );
    void buildPart2(String part2 );
    void buildPart3(String part3 );
    void buildPart4(String part4 );
    Product build();
}

class DefaultProductBuilder implements ProductBuilder{

    private String part1;
    private String part2;
    private String part3;
    private String part4;
    @Override
    public void buildPart1(String part1) {
        this.part1=part1;
    }

    @Override
    public void buildPart2(String part2) {
        this.part2=part2;
    }

    @Override
    public void buildPart3(String part3) {
        this.part3=part3;
    }

    @Override
    public void buildPart4(String part4) {
        this.part4=part4;
    }

    @Override
    public Product build() {
        return new Product(part1, part2, part3, part4 );
    }
}

class Director{

    private ProductBuilder productBuilder;

    public Director(ProductBuilder productBuilder) {
        this.productBuilder=productBuilder;
    }

    public Product makeProduct(String part1, String part2, String part3, String part4){
        productBuilder.buildPart1( part1 );
        productBuilder.buildPart2( part2 );
        productBuilder.buildPart3( part3 );
        productBuilder.buildPart4( part4 );
        Product product=productBuilder.build();
        return product;

    }

}





class Product{


    private String part1;
    private String part2;
    private String part3;
    private String part4;
    //  .....


    public Product(String part1, String part2, String part3, String part4) {
        this.part1=part1;
        this.part2=part2;
        this.part3=part3;
        this.part4=part4;
    }

    public String getPart1() {
        return part1;
    }

    public void setPart1(String part1) {
        this.part1=part1;
    }

    public String getPart2() {
        return part2;
    }

    public void setPart2(String part2) {
        this.part2=part2;
    }

    public String getPart3() {
        return part3;
    }

    public void setPart3(String part3) {
        this.part3=part3;
    }

    public String getPart4() {
        return part4;
    }

    public void setPart4(String part4) {
        this.part4=part4;
    }

    @Override
    public String toString() {
        return "Product{" +
                "part1='" + part1 + '\'' +
                ", part2='" + part2 + '\'' +
                ", part3='" + part3 + '\'' +
                ", part4='" + part4 + '\'' +
                '}';
    }
}

