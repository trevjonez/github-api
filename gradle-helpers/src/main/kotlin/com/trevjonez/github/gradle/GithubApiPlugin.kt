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

import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class GithubApiPlugin : Plugin<Project> {
  protected lateinit var target: Project
  protected lateinit var configExtension: GithubApiConfigurationExt

  override fun apply(target: Project) {
    this.target = target
    configExtension =
      target.extensions.create("GithubApi", GithubApiConfigurationExt::class.java)
    registerTasks()
  }

  abstract fun registerTasks()

  companion object {
    const val TASK_GROUP = "Github Api"
  }
}