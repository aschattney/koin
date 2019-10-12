/*
 * Copyright 2017-2018 the original author or authors.
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
package org.koin.androidx.viewmodel.ext.android

/**
 * Nested Scope extensions to help for ViewModel retrieving ViewModel inside definitions
 *
 * @author Andreas Schattney
 */

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.NestedScope

/**
 * Receive an instance of ViewModel V from the viewModelStoreOwner attached to LifecycleOwner L.
 * Please note that there must exist a definition for LifecycleOwner L to receive the instance of L.
 *
 * @param qualifier - Koin BeanDefinition qualifier (if have several ViewModel beanDefinition of the same type)
 * @param defaultArguments - Default arguments for SavedStateHandle if useState = true
 * @param parameters - parameters to pass to the BeanDefinition
 */
inline fun <reified L: LifecycleOwner, reified V: ViewModel> NestedScope.getViewModel(
        qualifier: Qualifier? = null,
        noinline defaultArguments: () -> Bundle? = { null },
        noinline parameters: ParametersDefinition? = null
): V = getViewModel(get<L>(), qualifier, defaultArguments, parameters)