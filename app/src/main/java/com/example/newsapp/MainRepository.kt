package com.example.newsapp

class MainRepository constructor(private val retrofitService: NewsAPI) {

    fun getAllArticles() = retrofitService.getArticles()
}