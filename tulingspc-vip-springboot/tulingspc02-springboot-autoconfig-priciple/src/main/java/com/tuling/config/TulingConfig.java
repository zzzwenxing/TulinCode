package com.tuling.config;

import com.tuling.compent.TulingBeanDefinitionRegister;
import com.tuling.compent.TulingImportSelector;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by smlz on 2019/3/28.
 */
@Configuration
//@Import(value = {TulingImportSelector.class})
@Import(value = {TulingImportSelector.class,TulingBeanDefinitionRegister.class})
public class TulingConfig {
}
