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

package com.trevjonez.github.releases

import com.squareup.moshi.JsonClass
import com.trevjonez.github.User
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.io.Serializable
import java.time.LocalDateTime

/**
 * https://developer.github.com/v3/repos/releases/
 */
@JsonClass(generateAdapter = true)
data class Release(
  val url: String,
  val html_url: String? = null,
  val assets_url: String? = null,
  val upload_url: String? = null,
  val tarball_url: String? = null,
  val zipball_url: String? = null,
  val id: Long,
  val node_id: String,
  val tag_name: String? = null,
  val target_commitish: String? = null,
  val name: String? = null,
  val body: String? = null,
  val draft: Boolean = false,
  val prerelease: Boolean = false,
  val created_at: LocalDateTime? = null,
  val published_at: LocalDateTime? = null,
  val author: User? = null,
  val assets: List<Asset> = emptyList()
) : Serializable {
  interface Api {
    @GET("/repos/{owner}/{repo}/releases")
    fun list(
      @Path("owner") owner: String,
      @Path("repo") repo: String,
      @Header("Authorization") auth: String
    ): Call<List<Release>>

    @GET("/repos/{owner}/{repo}/releases/{release_id}")
    fun byId(
      @Path("owner") owner: String,
      @Path("repo") repo: String,
      @Path("release_id") release_id: Long,
      @Header("Authorization") auth: String
    ): Call<Release>

    @GET("/repos/{owner}/{repo}/releases/latest")
    fun latest(
      @Path("owner") owner: String,
      @Path("repo") repo: String,
      @Header("Authorization") auth: String
    ): Call<Release>

    @GET("/repos/{owner}/{repo}/releases/tags/{tag}")
    fun byTag(
      @Path("owner") owner: String,
      @Path("repo") repo: String,
      @Path("tag") tag: String,
      @Header("Authorization") auth: String
    ): Call<Release>

    @POST("/repos/{owner}/{repo}/releases")
    @Headers("Content-Type: application/json; charset=utf-8")
    fun create(
      @Path("owner") owner: String,
      @Path("repo") repo: String,
      @Header("Authorization") auth: String,
      @Body body: Request
    ): Call<Release>

    @PATCH("/repos/{owner}/{repo}/releases/{release_id}")
    @Headers("Content-Type: application/json; charset=utf-8")
    fun edit(
      @Path("owner") owner: String,
      @Path("repo") repo: String,
      @Path("release_id") release_id: Long,
      @Header("Authorization") auth: String,
      @Body body: Request
    ): Call<Release>

    @DELETE("/repos/{owner}/{repo}/releases/{release_id}")
    fun delete(
      @Path("owner") owner: String,
      @Path("repo") repo: String,
      @Path("release_id") release_id: Long,
      @Header("Authorization") auth: String
    ): Call<Unit>

    @GET("/repos/{owner}/{repo}/releases/{release_id}/assets")
    fun listAssets(
      @Path("owner") owner: String,
      @Path("repo") repo: String,
      @Path("release_id") release_id: Long,
      @Header("Authorization") auth: String
    ): Call<List<Asset>>

    @POST
    fun uploadAsset(
      @Url url: String,
      @Query("name") name: String,
      @Header("Authorization") auth: String,
      @Header("Content-Type") contentType: String,
      @Body file: RequestBody
    ): Call<Release>

    @GET("/repos/{owner}/{repo}/releases/assets/{asset_id}")
    fun assetById(
      @Path("owner") owner: String,
      @Path("repo") repo: String,
      @Path("asset_id") asset_id: Long,
      @Header("Authorization") auth: String
    ): Call<List<Asset>>

    @PATCH("/repos/{owner}/{repo}/releases/assets/{asset_id}")
    fun editAsset(
      @Path("owner") owner: String,
      @Path("repo") repo: String,
      @Path("asset_id") asset_id: Long,
      @Header("Authorization") auth: String,
      @Body body: Asset.Edit
    ): Call<Asset>

    @DELETE("/repos/{owner}/{repo}/releases/assets/{asset_id}")
    fun editAsset(
      @Path("owner") owner: String,
      @Path("repo") repo: String,
      @Path("asset_id") asset_id: Long,
      @Header("Authorization") auth: String
    ): Call<Unit>
  }

  @JsonClass(generateAdapter = true)
  data class Request(
    val tag_name: String,
    val target_commitish: String?,
    val name: String?,
    val body: String?,
    val draft: Boolean?,
    val prerelease: Boolean?
  ) : Serializable

  @JsonClass(generateAdapter = true)
  data class Asset(
    val url: String,
    val browser_download_url: String,
    val id: Long,
    val node_id: String,
    val name: String,
    val label: String,
    val state: String,
    val content_type: String,
    val size: Long,
    val download_count: Long,
    val created_at: LocalDateTime,
    val updated_at: LocalDateTime,
    val uploader: User
  ) : Serializable {
    @JsonClass(generateAdapter = true)
    data class Edit(
      val name: String?,
      val label: String?
    ) : Serializable
  }
}
