/***
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package br.com.caelum.vraptor.validator;

import java.util.List;

import javax.validation.groups.Default;

/**
 * Implements a bean validator, delegating all validation to Bean Validator especification.
 * 
 * @author Otávio Scherer Garcia
 * @since vraptor3.1.2
 */
public interface BeanValidator {

    /**
     * Validate the bean and return a list of messages if has constraint violations. If the object is null, an empty
     * list will be returned.
     *
     * @param object The object to be validated.
     * @param groups The groups that must be validated.
     * @return List of constraint violations.
     */
    List<Message> validate(Object object, Class<?>... groups);
    
    /**
     * Validate the specified bean's properties and return a list of messages if has constraint violations. 
     * If the object is null, an empty list will be returned. Only validate properties of {@link Default} group.
     * 
     * @param object The object to be validated.
     * @param properties The properties that must be validated. This properties must belong to {@link Default} group to be validated.
     * @return List of constraint violations.
     */
    List<Message> validateProperties(Object object, String... properties);

}
