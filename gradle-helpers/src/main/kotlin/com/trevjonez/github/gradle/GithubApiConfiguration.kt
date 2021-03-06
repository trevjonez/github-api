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

import groovy.lang.Closure
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider

interface GithubApiConfiguration {
  val apiUrl: Property<String>
  val owner: Property<String>
  val repo: Property<String>
  val authToken: Property<String>

  fun apiUrl(value: Any) = apiUrl.dslFun(value)
  fun owner(value: Any) = owner.dslFun(value)
  fun repo(value: Any) = repo.dslFun(value)
  fun authToken(value: Any) = authToken.dslFun(value)
}

@JvmName(name = "stringPropertyDslFun")
fun Property<String>.dslFun(value: Any) {
  when (value) {
    is CharSequence -> set(value.toString())
    is Provider<*> -> set(value.map {
      when (it) {
        is CharSequence -> it.toString()
        else -> throw IllegalArgumentException("Unable to handle type: ${value.javaClass} as input to Property<String>")
      }
    })
    else -> throw IllegalArgumentException("Unable to handle type: ${value.javaClass} as input to Property<String>")
  }
}

inline fun <reified T : Any> Property<T>.dslFun(value: Any) {
  @Suppress("UNCHECKED_CAST")
  when (value) {
    is T -> set(value)
    is Provider<*> -> set(value as Provider<out T>)
    else -> throw IllegalArgumentException("Unable to handle type: ${value.javaClass} as input to Property<${T::class.java}>")
  }
}