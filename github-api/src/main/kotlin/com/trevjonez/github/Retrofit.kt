package com.trevjonez.github

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

fun defaultRetrofit() = Retrofit.Builder()
  .client(
    OkHttpClient.Builder()
      .addInterceptor(headerInterceptor("Accept", "application/vnd.github.v3+json"))
      .build()
  )
  .baseUrl(HttpUrl.get("https://api.github.com"))
  .addConverterFactory(MoshiConverterFactory.create())
  .addConverterFactory(ScalarsConverterFactory.create())
  .build()

fun headerInterceptor(name: String, value: String) = Interceptor { chain ->
  chain.proceed(
    chain.request()
      .newBuilder()
      .header(name, value)
      .build()
  )
}