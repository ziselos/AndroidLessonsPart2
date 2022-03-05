package com.example.androidlessonspart2.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.androidlessonspart2.adapters.NewsCategoriesAdapter
import com.example.androidlessonspart2.adapters.NewsCategory
import com.example.androidlessonspart2.databinding.ActivityNewsCategoriesBinding

class NewsCategoriesActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewsCategoriesBinding
    lateinit var newsCategoriesAdapter: NewsCategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNewsCategories()
    }

    private fun initNewsCategories() {
        binding.apply {
            newsCategoriesAdapter = NewsCategoriesAdapter(onCategoryClicked = { category ->
                val intent = Intent(this@NewsCategoriesActivity, NewsByCategoryActivity::class.java)
                intent.putExtra("category", category)
                startActivity(intent)

//                Intent(this@MainActivity, NewsByCategoryActivity::class.java).also {
//                    it.putExtra("category", category)
//                    startActivity(it)
//                }

            })
            newsCategoriesAdapter.setList(getNewsCategories())
            categoriesRecyclerView.adapter = newsCategoriesAdapter
        }
    }
}

// i create the hardcoded the list with news categories
fun getNewsCategories(): ArrayList<NewsCategory> {
    val listToReturn = arrayListOf<NewsCategory>()
    listToReturn.add(NewsCategory(title = "All"))
    listToReturn.add(NewsCategory(title = "national"))
    listToReturn.add(NewsCategory(title = "business"))
    listToReturn.add(NewsCategory(title = "sports"))
    listToReturn.add(NewsCategory(title = "world"))
    listToReturn.add(NewsCategory(title = "politics"))
    listToReturn.add(NewsCategory(title = "technology"))
    listToReturn.add(NewsCategory(title = "startup"))
    listToReturn.add(NewsCategory(title = "entertainment"))
    listToReturn.add(NewsCategory(title = "miscellaneous"))
    listToReturn.add(NewsCategory(title = "hatke"))
    listToReturn.add(NewsCategory(title = "science"))
    listToReturn.add(NewsCategory(title = "automobile"))
    return listToReturn

}
