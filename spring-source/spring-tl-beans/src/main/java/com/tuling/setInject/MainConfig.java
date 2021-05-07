package com.tuling.setInject;


import com.tuling.setInject.TulingImportBeanDefinitionRegister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by smlz on 2019/8/22.
 */
@ComponentScan(basePackages = {"com.tuling.setInject"})
//@Import(value = {InstC.class,TulingImportBeanDefinitionRegister.class,TulingImportSelector.class})
//@Import(value = {InstC.class})
//@Import(value = {TulingImportBeanDefinitionRegister.class})
@Import(value = {TulingImportSelector.class})
@Configuration
public class MainConfig {

/*	@Bean
	public InstB instB2() {
		return new InstB();
	}*/

}
