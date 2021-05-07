package com.tuling.spring;/**
 * Created by Administrator on 2018/8/26.
 */

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Tommy
 *         Created by Tommy on 2018/8/26
 **/
public class BeanDefinitionReaderExample {

    public static void main(String[] args) {
        // 注册 中心
        BeanDefinitionRegistry register = new SimpleBeanDefinitionRegistry();
        //  读取器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(register);
        // 资源读取器
        DefaultResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("spring.xml");
        // 装载构建Bean的定义
        reader.loadBeanDefinitions(resource);

        register.getAliases("hi");
        register.getBeanDefinition("hi");
        System.out.println(Arrays.toString(register.getBeanDefinitionNames()));
    }
}
