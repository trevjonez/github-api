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

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.time.LocalDateTime
import java.time.ZonedDateTime

fun defaultRetrofit(baseUrl: String = DEFAULT_BASE_URL) = Retrofit.Builder()
  .client(
    OkHttpClient.Builder()
      .addInterceptor(headerInterceptor("Accept", "application/vnd.github.v3+json"))
      .build()
  )
  .baseUrl(HttpUrl.get(baseUrl))
  .addConverterFactory(MoshiConverterFactory.create(defaultMoshi()))
  .addConverterFactory(ScalarsConverterFactory.create())
  .build()

fun defaultMoshi(): Moshi = Moshi.Builder()
  .add(LocalDateTimeAdapter())
  .build()

fun headerInterceptor(name: String, value: String) = Interceptor { chain ->
  chain.proceed(
    chain.request()
      .newBuilder()
      .header(name, value)
      .build()
  )
}

class LocalDateTimeAdapter {
  @FromJson
  fun from(json: String): LocalDateTime {
    return ZonedDateTime.parse(json).toLocalDateTime()
  }

  @ToJson
  fun to(time: LocalDateTime): String {
    return time.toString()
  }
}