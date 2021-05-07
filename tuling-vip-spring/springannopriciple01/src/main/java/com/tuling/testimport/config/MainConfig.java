package com.tuling.testimport.config;

import com.tuling.testimport.compent.Car;
import com.tuling.testimport.compent.Person;
import com.tuling.testimport.importselect.TulingBeanDefinitionRegister;
import com.tuling.testimport.importselect.TulingImportSelector;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by smlz on 2019/5/20.
 */
@Configuration
//@Import(value = {Person.class, Car.class})
//@Import(value = {Person.class, Car.class, TulingImportSelector.class})
@Import(value = {Person.class, Car.class, TulingImportSelector.class, TulingBeanDefinitionRegister.class})
public class MainConfig {
}
