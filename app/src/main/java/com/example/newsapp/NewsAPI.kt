package com.example.newsapp

import com.example.newsapp.Article
import retrofit2.Response
import retrofit2.http.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface NewsAPI {

    @GET("everything")
    fun getArticles(@Query("q") key: String = "bitcoin", @Query("apiKey")
    key2: String = "a152a627967242f78b6e06ddd0a7b8d2"): Call<Bigresponse>

    companion object {
        val client = OkHttpClient.Builder()
            .build()

        var retrofitService: NewsAPI? = null

        fun getInstance() : NewsAPI {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                retrofitService = retrofit.create(NewsAPI::class.java)
            }
            return retrofitService!!

        }
    }

}