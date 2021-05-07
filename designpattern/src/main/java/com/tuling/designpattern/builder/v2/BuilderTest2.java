package com.tuling.designpattern.builder.v2;

/**
 * @author 腾讯课堂-图灵学院  郭嘉
 * @Slogan 致敬大师，致敬未来的你
 */
public class BuilderTest2 {
    public static void main(String[] args) {

        Product.Builder builder=new Product.Builder().builderPart1( "part1" ).builderPart2( "part2" );

        if (true){
            builder.builderPart3( "part3" );
        }

        Product.Builder part4=builder.builderPart4( "part4" );
        Product product=part4.build();
        System.out.println(product);
    }
}
class Product {


    private final String part1;
    private final String part2;
    private final String part3;
    private final String part4;
    //  .....


    public Product(String part1, String part2, String part3, String part4 ) {
        this.part1=part1;
        this.part2=part2;
        this.part3=part3;
        this.part4=part4;
    }

    public String getPart1() {
        return part1;
    }

    public String getPart2() {
        return part2;
    }

    public String getPart3() {
        return part3;
    }

    public String getPart4() {
        return part4;
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



    static class Builder{
        private   String part1;
        private   String part2;
        private   String part3;
        private   String part4;

        public Builder builderPart1(String part1){
            this.part1=part1;
            return this;
        }

        public Builder builderPart2(String part2){
            this.part2=part2;
            return this;
        }

        public Builder builderPart3(String part3){
            this.part3=part3;
            return this;
        }
        public Builder builderPart4(String part4){
            this.part4=part4;
            return this;
        }

        Product build(){
            return new Product( part1,part2,part3,part4 );
        }

    }


}

