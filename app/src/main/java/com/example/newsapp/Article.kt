package com.example.newsapp

import android.accounts.AuthenticatorDescription
import java.util.*

data class Bigresponse(
    val articles: List<Article> = listOf()
)

data class Article(
    val id: Int? = null,
    val source: Source? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: Date? = null,
    val content: String? = null
)

data class Source(
    val id: String? = null,
    val name: String? = null
)