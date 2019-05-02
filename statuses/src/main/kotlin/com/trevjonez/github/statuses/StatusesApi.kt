package com.trevjonez.github.statuses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.trevjonez.github.Repository
import com.trevjonez.github.User
import retrofit2.Call
import retrofit2.http.*
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class Status(
  val url: String,
  val avatar_url: String,
  val id: Long,
  val node_id: String,
  val state: State,
  val description: String,
  val target_url: String,
  val context: String,
  val created_at: LocalDateTime,
  val updated_at: LocalDateTime,
  val creator: User
) {
  interface Api {
    @POST("/repos/{owner}/{repo}/statuses/{sha}")
    @Headers("Content-Type: application/json; charset=utf-8")
    fun create(
      @Path("owner") owner: String,
      @Path("repo") repo: String,
      @Path("sha") sha: String,
      @Header("Authorization") auth: String,
      @Body request: Request
    ): Call<Status>

    @GET("/repos/{owner}/{repo}/commits/{ref}/statuses")
    fun list(
      @Path("owner") owner: String,
      @Path("repo") repo: String,
      @Path("ref") sha: String,
      @Header("Authorization") auth: String
    ): Call<List<Status>>

    @GET("/repos/{owner}/{repo}/commits/{ref}/status")
    fun combined(
      @Path("owner") owner: String,
      @Path("repo") repo: String,
      @Path("ref") sha: String,
      @Header("Authorization") auth: String
    ): Call<Combined>
  }

  @JsonClass(generateAdapter = true)
  data class Request(
    val state: State,
    val target_url: String,
    val description: String,
    val context: String
  )

  enum class State {
    @Json(name = "error")
    ERROR,
    @Json(name = "failure")
    FAILURE,
    @Json(name = "pending")
    PENDING,
    @Json(name = "success")
    SUCCESS;
  }

  @JsonClass(generateAdapter = true)
  data class Combined(
    val state: State,
    val statuses: List<Status>,
    val sha: String,
    val total_count: Long,
    val repository: Repository,
    val commit_url: String,
    val url: String
  )
}
