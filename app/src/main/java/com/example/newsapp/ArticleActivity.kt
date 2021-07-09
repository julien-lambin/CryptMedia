package com.example.newsapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ActivityMainBinding;
import com.example.newsapp.ui.login.LoginActivity
import io.realm.Realm
import io.realm.mongodb.sync.SyncConfiguration

class ArticleActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = NewsAPI.getInstance()
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter


        viewModel.articleList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setArticleList(it)
            binding.swipeToRefresh.isRefreshing=false
        })

        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.getAllArticles()

        refreshApp()

    }
    private fun refreshApp(){
        binding.swipeToRefresh.setOnRefreshListener{
            Toast.makeText(this,"Page actualisée" , Toast.LENGTH_LONG).show()
            viewModel.getAllArticles()
        }
    }
}