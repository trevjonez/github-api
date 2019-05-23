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

import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider

interface GithubApiConfiguration {
  val owner: Property<String>
  val repo: Property<String>
  val authToken: Property<String>

  fun owner(value: Any) = dslFun(owner, value)
  fun repo(value: Any) = dslFun(repo, value)
  fun authToken(value: Any) = dslFun(authToken, value)
}

private fun dslFun(prop: Property<String>, value: Any) {
  when (value) {
    is CharSequence -> prop.set(value.toString())
    is Provider<*> -> prop.set(value.map { it.toString() })
  }
}