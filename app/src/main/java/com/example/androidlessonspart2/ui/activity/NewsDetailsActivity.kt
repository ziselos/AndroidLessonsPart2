package com.example.androidlessonspart2.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlessonspart2.databinding.ActivityNewsDetailsBinding
import com.example.androidlessonspart2.models.api.Data

class NewsDetailsActivity: AppCompatActivity() {

    companion object {
        const val ARTICLE_ARG = "ARTICLE"
    }

    private lateinit var binding: ActivityNewsDetailsBinding

    private lateinit var article: Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // here we receive the article object
        intent?.extras.apply {
            article = this?.getParcelable<Data>(ARTICLE_ARG) as Data
        }
        bindArticleInfo(article)
    }


    private fun bindArticleInfo(article: Data) {
        binding.apply {
            title.text = article.title
            author.text = article.author
            date.text = article.date
            content.text = article.content
        }
    }




}