package it.edu.unsafe;

import it.edu.utils.UnsafeInstance;

/**
 * @author ：图灵-杨过
 * @date：2019/8/2
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class FenceTest {

    public static void main(String[] args) {

        UnsafeInstance.reflectGetUnsafe().loadFence();//读屏障

        UnsafeInstance.reflectGetUnsafe().storeFence();//写屏障

        UnsafeInstance.reflectGetUnsafe().fullFence();//读写屏障

    }
}
