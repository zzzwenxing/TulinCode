/*
 * Copyright 2002-2007 the original author or authors.
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

package org.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * Common marker interface for after advice,
 * such as {@link AfterReturningAdvice} and {@link ThrowsAdvice}.
 *
 * @author Juergen Hoeller
 * @since 2.0.3
 * @see BeforeAdvice
 */
/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:后置通知接口同样也是标记接口
* @author: smlz
* @createDate: 2019/7/30 15:54
* @version: 1.0
*/
public interface AfterAdvice extends Advice {

}
