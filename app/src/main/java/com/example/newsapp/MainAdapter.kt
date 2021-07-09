package com.example.newsapp

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


import com.example.newsapp.databinding.AdapterArticleBinding

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {

    var article = mutableListOf<Article>()

    fun setArticleList(articles: List<Article>) {
        this.article = articles.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterArticleBinding.inflate(inflater, parent, false)

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val article = article[position]
        val url = article.url
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        holder.binding.articleTitle.text = article.title
        holder.binding.articleAuthor.text = article.author
        holder.binding.articleContent.text = article.content
        Glide.with(holder.itemView.context).load(article.urlToImage).into(holder.binding.articleImage)
        holder.binding.articleButton.setOnClickListener {
            // Do whatever you want on your button click as like you did to your recycler-view item click
            //action.onCardClick(item, adapterPosition)

            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse(url)
            startActivity(holder.itemView.context,openURL,null)

        }



    }

    override fun getItemCount(): Int {
        return article.size
    }

}

class MainViewHolder(val binding: AdapterArticleBinding) : RecyclerView.ViewHolder(binding.root) {

}