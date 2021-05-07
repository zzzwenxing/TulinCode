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
 * Common marker interface for before advice, such as {@link MethodBeforeAdvice}.
 *
 * <p>Spring supports only method before advice. Although this is unlikely to change,
 * this API is designed to allow field advice in future if desired.
 *
 * @author Rod Johnson
 * @see AfterAdvice
 */
/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:aop的二级接口,该接口是我们的标记接口,标识我们的通知是前置通知.
 *       作用二: 用于扩展
* @author: smlz
* @createDate: 2019/7/30 15:43
* @version: 1.0
*/
public interface BeforeAdvice extends Advice {

}
