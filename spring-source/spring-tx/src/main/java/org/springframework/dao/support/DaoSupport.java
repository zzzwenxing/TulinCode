/*
 * Copyright 2002-2012 the original author or authors.
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

package org.springframework.dao.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;

/**
 * Generic base class for DAOs, defining template methods for DAO initialization.
 *
 * <p>Extended by Spring's specific DAO support classes, such as:
 * JdbcDaoSupport, JdoDaoSupport, etc.
 *
 * @author Juergen Hoeller
 * @since 1.2.2
 */
public abstract class DaoSupport implements InitializingBean {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());


	/**
	 * 方法实现说明:我们的UserMapper<MapperFactoryBean>的生命周期回调的时候会回调
	 * InitializingBean.afterPropertiesSet()来检查我们的配置
	 * InitializingBean
	 *   --DaoSupport
	 *   	--SqlSessionDaoSupport
	 *   	  --MapperFactoryBean
	 * @author:smlz
	 * @return:
	 * @exception:
	 * @date:2019/8/22 19:30
	 */
	@Override
	public final void afterPropertiesSet() throws IllegalArgumentException, BeanInitializationException {
		/**
		 * 检查我们Dao的配置,mybaits的配置就会调用子类SqlSessionDaoSupport的checkDaoConfig();
		 */
		checkDaoConfig();

		// Let concrete implementations initialize themselves.
		try {
			initDao();
		}
		catch (Exception ex) {
			throw new BeanInitializationException("Initialization of DAO failed", ex);
		}
	}

	/**
	 * Abstract subclasses must override this to check their configuration.
	 * <p>Implementors should be marked as {@code final} if concrete subclasses
	 * are not supposed to override this template method themselves.
	 * @throws IllegalArgumentException in case of illegal configuration
	 */
	protected abstract void checkDaoConfig() throws IllegalArgumentException;

	/**
	 * Concrete subclasses can override this for custom initialization behavior.
	 * Gets called after population of this instance's bean properties.
	 * @throws Exception if DAO initialization fails
	 * (will be rethrown as a BeanInitializationException)
	 * @see org.springframework.beans.factory.BeanInitializationException
	 */
	protected void initDao() throws Exception {
	}

}
