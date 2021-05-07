package it.edu.unsafe;

import it.edu.utils.UnsafeInstance;
import sun.misc.Unsafe;

/**
 * @author ：图灵-杨过
 * @date：2019/8/2
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class DirectMemoryAccessTest {

    public static void main(String[] args) {

        Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();

        long oneHundred = 1;
        byte size = 1;

        /*
         * 调用allocateMemory分配内存
         */
        long memoryAddress = unsafe.allocateMemory(size);

        /*
         * 将1写入到内存中
         */
        unsafe.putAddress(memoryAddress, oneHundred);

        /*
         * 内存中读取数据
         */
        long readValue = unsafe.getAddress(memoryAddress);

        System.out.println("value : " + readValue);
    }
}
