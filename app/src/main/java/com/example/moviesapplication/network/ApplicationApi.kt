package com.example.moviesapplication.network

import com.example.moviesapplication.model.SingleMovie
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://raw.githubusercontent.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface ApplicationRepository {
    @GET("/FEND16/movie-json-data/master/json/movies-coming-soon.json")
    fun getMovies(): Call<List<SingleMovie>>


}


object ApplicationApi {
    val retrofitService : ApplicationRepository by lazy {
        retrofit.create(ApplicationRepository::class.java)
    }
}