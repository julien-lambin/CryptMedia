package com.example.newsapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val articleList = MutableLiveData<List<Article>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllArticles() {
        val request = repository.getAllArticles()
        request.enqueue(object : Callback<Bigresponse>{

            override fun onResponse(call: Call<Bigresponse>, response: Response<Bigresponse>) {
                if (response.isSuccessful){
                    articleList.postValue(response.body()?.articles!!)
                }
            }

            override fun onFailure(call: Call<Bigresponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}