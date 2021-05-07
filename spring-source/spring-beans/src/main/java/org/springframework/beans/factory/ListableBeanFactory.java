/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory;

import java.lang.annotation.Annotation;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

/**
 ListableBeanFactory接口是BeanFactory接口的一个扩展。实现了此接口的类一般都有预加载bean定义功能（从XML等配置文件中），因此都有能列举其包含的所有Bean，根据名字或其它单个查找Bean的特性。
 理论上讲，即使此类的实现类同时也实现了HierarchicalBeanFactory，此接口中方法返回值也不应该考虑其父BeanFactory中的Bean，但是你可以通过BeanFactoryUtils工具类来获取哪些在父BeanFactory中的Bean。
 此接口中的所有方法只会考虑本BeanFactory实例中的Bean定义，除了getBeanNamesOfType和getBeanOfType以外，其他方法都不会考虑其他方法诸如的bean实例,诸如通过ConfigurableBeanFactory的registerSingleton方法配置的单例。这主要也是因为getBean方法可以访问这些手动配置的Bean定义。但是，通常情况下，所有的Bean都是通过外部的配置文件配置的，因此不必担心以上这点区别。
 需要注意的是，除了getBeanDefinitionCount和containsBeanDefinition外，此接口中其他方法的实现可能不会太高效，因此，不建议经常调用。
 * BeanFactory的二级接口
 *
 */
public interface ListableBeanFactory extends BeanFactory {

	/**
	 * 查看是否包含指定名字的Bean
	 * 不支持向上或向下查找
	 * 不支持查找非配置文件定义的单例Bean
	 */
	boolean containsBeanDefinition(String beanName);

	/**
	 * 获取容器中的所有bean定义
	 * @return the number of beans defined in the factory
	 */
	int getBeanDefinitionCount();

	/**
	 * 查看此BeanFactory中包含的Bean数量
	 * 不支持向上或向下查找
	 * 不支持查找非配置文件定义的单例Bean
	 */
	String[] getBeanDefinitionNames();

	/**
	 * 返回此BeanFactory中所包含的所有Bean定义的名称
	 * 不支持向上或向下查找
	 * 不支持查找非配置文件定义的单例Bean
	 */
	String[] getBeanNamesForType(ResolvableType type);

	/**
	 * 返回此BeanFactory中所有指定类型的Bean的名字。判断是否是指定类型的标准有两个：a Bean定义。
	 * b FactoryBean的getObjectType方法。
	 * 只考虑最顶层的Bean，对于嵌套的Bean，即使符合类型也不予考虑
	 * 会考虑FactoryBean创建出的Bean
	 * 不支持向上或向下查找
	 * 不支持查找非配置文件定义的单例Bean
	 * 此签名的getBeanNamesForType方法会返回所有Scope类型的Bean，在大多数的实现中，其返回结果和
	 * 其重载方法getBeanNamesForType(type, true, true)返回结果一致。
	 * 返回结果中，Bean名字的顺序应该和其定义时一样
	 */
	String[] getBeanNamesForType(@Nullable Class<?> type);

	/**
	 * 返回此BeanFactory中所有指定类型（或指定类型的子类型）的Bean的名字。判断是否是指定类型的标准有
	 * 两个：a Bean定义，b FactoryBean的getObjectType方法。
	 * 只考虑最顶层的Bean，对于嵌套的Bean，即使符合类型也不予考虑
	 * 会考虑FactoryBean创建出的Bean
	 * 不支持向上或向下查找
	 * 不支持查找非配置文件定义的单例Bean
	 * 此签名的getBeanNamesForType方法会返回所有Scope类型的Bean，在大多数的实现中，其返回结果和
	 * 其重载方法getBeanNamesForType(type, true, true)返回结果一致。
	 * 返回结果中，Bean名字的顺序应该和其定义时一样
	 */
	String[] getBeanNamesForType(@Nullable Class<?> type, boolean includeNonSingletons, boolean allowEagerInit);

	/**
	 * 返回此BeanFactory中所有指定类型（或指定类型的子类型）的Bean的名字。判断是否是指定类型的标准有
	 * 两个：a Bean定义，b FactoryBean的getObjectType方法。
	 * 只考虑最顶层的Bean，对于嵌套的Bean，即使符合类型也不予考虑
	 * 会考虑FactoryBean创建出的Bean
	 * 不支持向上或向下查找
	 * 不支持查找非配置文件定义的单例Bean
	 * 此签名的getBeanNamesForType方法会返回所有Scope类型的Bean，在大多数的实现中，其返回结果和
	 * 其重载方法getBeanNamesForType(type, true, true)返回结果一致。
	 * 返回结果中，Bean名字的顺序应该和其定义时一样
	 * 如果Bean是通过FactoryBean创建的，那么只考虑设置了allowEagerInit标志位的Bean。如果
	 * 没有设置allowEagerInit标志位，则只考虑FactoryBean的类型
	 */
	<T> Map<String, T> getBeansOfType(@Nullable Class<T> type) throws BeansException;

	/**
	 * 返回此BeanFactory中所有指定类型（或指定类型的子类型）的Bean的名字。判断是否是指定类型的标准有
	 * 两个：a Bean定义，b FactoryBean的getObjectType方法。
	 * 只考虑最顶层的Bean，对于嵌套的Bean，即使符合类型也不予考虑
	 * 会考虑FactoryBean创建出的Bean
	 * 不支持向上或向下查找
	 * 不支持查找非配置文件定义的单例Bean
	 * 此签名的getBeanNamesForType方法会返回所有Scope类型的Bean，在大多数的实现中，其返回结果和
	 * 其重载方法getBeanNamesForType(type, true, true)返回结果一致。
	 * 返回结果中，Bean名字的顺序应该和其定义时一样
	 */
	<T> Map<String, T> getBeansOfType(@Nullable Class<T> type, boolean includeNonSingletons, boolean allowEagerInit)
			throws BeansException;

	/**
	 * 找到所有带有指定注解类型的Bean
	 */
	String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType);

	/**
	 * 找到所有带有指定注解的Bean，返回一个以Bean的name为键，其对应的Bean实例为值的Map
	 */
	Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) throws BeansException;

	/**
	 * 在指定name对应的Bean上找指定的注解，如果没有找到的话，去指定Bean的父类或者父接口上查找
	 */
	@Nullable
	<A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType)
			throws NoSuchBeanDefinitionException;

}
