/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.moduliths.moduleB.internal;

import org.springframework.stereotype.Component;

/**
 * @author Oliver Gierke
 */
@Component
public class InternalComponentB {

    public String sayHelloBIn() {
        System.out.printf("this is BIn");

        SupportingComponentB supportingComponentB = new SupportingComponentB();
        return "this is BIn" + supportingComponentB.sayHelloBIns();

    }

}
