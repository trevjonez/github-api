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

import com.trevjonez.github.defaultRetrofit
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import retrofit2.Retrofit
import retrofit2.create
import kotlin.properties.ReadOnlyProperty

abstract class GithubApiTask : DefaultTask(), GithubApiConfiguration {

  @get:Input
  abstract override val apiUrl: Property<String>

  @get:Input
  abstract override val owner: Property<String>

  @get:Input
  abstract override val repo: Property<String>

  @get:Input
  abstract override val authToken: Property<String>

  val retrofit: Retrofit by projectExt({ apiUrl.get() }) { defaultRetrofit(apiUrl.get()) }

  protected inline fun <reified T : Any> githubApi(): ReadOnlyProperty<GithubApiTask, T> {
    return projectExt { retrofit.create<T>() }
  }

  fun setApiConfig(apiConfig: GithubApiConfiguration) {
    apiUrl.set(apiConfig.apiUrl)
    owner.set(apiConfig.owner)
    repo.set(apiConfig.repo)
    authToken.set(apiConfig.authToken)
  }
}