package com.tuling.designpattern.strategy.v3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author 腾讯课堂-图灵学院  郭嘉
 * @Slogan 致敬大师，致敬未来的你
 */
public class ComparatorTest {
    public static void main(String[] args) {
        Person[] persons=
                new Person[]{ new Person( 10,111 ),new Person( 18,99 ),new Person( 15,122 )};

        Arrays.sort( persons, new SortByHeight());

        print( persons );

    }

    static void print(Person[] array  ){
        for (int i =0;i<array.length;i++){
            System.out.println(array[i]);
        }

    }
}
//策略1 根据年龄排序
class SortByAge implements Comparator<Person>{

    @Override
    public int compare(Person o1, Person o2) {
        if (o1.getAge()>o2.getAge()){
            return 1;
        }else if (o1.getAge()<o2.getAge()){
            return -1;
        }
        return 0;
    }
}

//策略2 根据身高排序
class SortByHeight implements Comparator<Person>{

    @Override
    public int compare(Person o1, Person o2) {
        if (o1.getHeight()>o2.getHeight()){
            return 1;
        }else if (o1.getHeight()<o2.getHeight()){
            return -1;
        }
        return 0;
    }
}

class Person{

    int age;
    int height;

    public Person(int age, int height) {
        this.age=age;
        this.height=height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age=age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height=height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", height=" + height +
                '}';
    }
}
