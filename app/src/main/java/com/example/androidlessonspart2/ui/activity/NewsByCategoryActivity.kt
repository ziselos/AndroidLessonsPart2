package com.example.androidlessonspart2.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlessonspart2.adapters.NewsByCategoryAdapter
import com.example.androidlessonspart2.adapters.NewsCategory
import com.example.androidlessonspart2.api.APIInterface
import com.example.androidlessonspart2.api.NewsAPIClient
import com.example.androidlessonspart2.databinding.ActivityNewsByCategoryBinding
import com.example.androidlessonspart2.models.api.Data
import kotlinx.coroutines.*

class NewsByCategoryActivity : AppCompatActivity() {

    companion object {
        const val ARTICLE_ARG = "ARTICLE"
    }

    private lateinit var binding: ActivityNewsByCategoryBinding
    private lateinit var category: NewsCategory
    lateinit var adapter: NewsByCategoryAdapter

    // about fetching data from api service
    private var apiInterface: APIInterface? = null
    private lateinit var job: Job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsByCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.apply {
            category = this.getParcelable<NewsCategory>("category") as NewsCategory
        }
        initRecyclerView()
        getNewsForCategory(category)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        Log.d("Test", "OnDestroy called so we have to cancel job")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Test", "onPause called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Test", "onResume called")
    }

    private fun initRecyclerView() {
        binding.apply {
            adapter = NewsByCategoryAdapter(onItemClick = { article ->
                Intent(this@NewsByCategoryActivity, NewsDetailsActivity::class.java).also {
                    it.putExtra(ARTICLE_ARG, article)
                    startActivity(it)
                }
            })
            categoryArticlesRecyclerView.adapter = adapter
        }
    }

    private fun getNewsForCategory(category: NewsCategory) {
        apiInterface = NewsAPIClient.newsClient?.create(APIInterface::class.java)
        binding.loader.visibility = View.VISIBLE
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = apiInterface?.getNewsByCategory(category.title)
            withContext(Dispatchers.Main) {
                binding.loader.visibility = View.GONE
                if (response?.isSuccessful == true) {
                    if (response.body()?.success == true) {
                        adapter.setList(response.body()?.data as ArrayList<Data>)
                        adapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(
                            this@NewsByCategoryActivity,
                            "Something went wrong!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@NewsByCategoryActivity,
                        response?.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }


}