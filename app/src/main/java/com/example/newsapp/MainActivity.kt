package com.example.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.ui.login.LoginActivity
import io.realm.*
import io.realm.kotlin.where
import io.realm.mongodb.App
import io.realm.mongodb.sync.SyncConfiguration
import org.bson.types.ObjectId


class MainActivity : AppCompatActivity() {
    private var user: io.realm.mongodb.User? = null
    private var userRealm: Realm? = null

    override fun onStart() {
        super.onStart()

        println(taskApp)
        println("-----------------------------ICICI------------------")
        user = taskApp.currentUser()
        println("-----------------------------ICICI------------------")
        if (user == null) {
            // if no user is currently logged in, start the login activity so the user can authenticate
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, ArticleActivity::class.java))
            // configure realm to use the current user and the partition corresponding to the user's project
            val config = SyncConfiguration.Builder(user!!, "user=${user!!.id}")
                .build()
            // Sync all realm changes via a new instance, and when that instance has been successfully created connect it to an on-screen list (a recycler view)
            Realm.getInstanceAsync(config, object: Realm.Callback() {
                override fun onSuccess(realm: Realm) {
                    // since this realm should live exactly as long as this activity, assign the realm to a member variable
                    this@MainActivity.userRealm = realm

                }
            })
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}