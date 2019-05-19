/*
 *    Copyright 2019 Trevor Jones
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.trevjonez.github.gradle

import org.gradle.api.Task
import org.gradle.api.plugins.ExtensionContainer
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T : Any> projectExt(
  crossinline defaultValue: () -> T
): ReadOnlyProperty<Task, T> {
  return object : ReadOnlyProperty<Task, T> {
    override fun getValue(thisRef: Task, property: KProperty<*>): T {
      return thisRef.project.extensions.getValue(defaultValue)
    }
  }
}

inline fun <reified T : Any> ExtensionContainer.getValue(
  crossinline defaultValue: () -> T
): T {
  val name = "GithubApi:${T::class.java.simpleName}"
  return findByName(name) as? T ?: defaultValue().also { add(name, it) }
}