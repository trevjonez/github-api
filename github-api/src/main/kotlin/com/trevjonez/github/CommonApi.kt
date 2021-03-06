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

package com.trevjonez.github

import com.squareup.moshi.JsonClass
import java.io.Serializable

const val DEFAULT_BASE_URL = "https://api.github.com"

@JsonClass(generateAdapter = true)
data class User(
  val login: String,
  val id: Long,
  val node_id: String,
  val avatar_url: String,
  val gravatar_id: String,
  val url: String,
  val html_url: String,
  val followers_url: String,
  val following_url: String,
  val gists_url: String,
  val starred_url: String,
  val subscriptions_url: String,
  val organizations_url: String,
  val repos_url: String,
  val events_url: String,
  val received_events_url: String,
  val type: String,
  val site_admin: Boolean
) : Serializable

@JsonClass(generateAdapter = true)
data class Repository(
  val id: Long,
  val node_id: String,
  val name: String,
  val full_name: String,
  val owner: User,
  val private: Boolean,
  val html_url: String,
  val description: String,
  val fork: Boolean,
  val url: String,
  val archive_url: String,
  val assignees_url: String,
  val blobs_url: String,
  val branches_url: String,
  val collaborators_url: String,
  val comments_url: String,
  val commits_url: String,
  val compare_url: String,
  val contents_url: String,
  val contributors_url: String,
  val deployments_url: String,
  val downloads_url: String,
  val events_url: String,
  val forks_url: String,
  val git_commits_url: String,
  val git_refs_url: String,
  val git_tags_url: String,
  val git_url: String,
  val issue_comment_url: String,
  val issue_events_url: String,
  val issues_url: String,
  val keys_url: String,
  val labels_url: String,
  val languages_url: String,
  val merges_url: String,
  val milestones_url: String,
  val notifications_url: String,
  val pulls_url: String,
  val releases_url: String,
  val ssh_url: String,
  val stargazers_url: String,
  val statuses_url: String,
  val subscribers_url: String,
  val subscription_url: String,
  val tags_url: String,
  val teams_url: String,
  val trees_url: String
) : Serializable
