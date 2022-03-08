package com.example.androidlessonspart2.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.androidlessonspart2.databinding.ActivityHomeBinding

class HomeActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        installSplashScreen()
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        binding.apply {
            news.setOnClickListener {
                Intent(this@HomeActivity, NewsCategoriesActivity::class.java).also {
                    startActivity(it)
                }
            }

            disney.setOnClickListener {
                Intent(this@HomeActivity, DisneyCharactersActivity::class.java).also {
                    startActivity(it)
                }
            }

            weather.setOnClickListener {
                Intent(this@HomeActivity, WeatherCitiesActivity::class.java).also {
                    startActivity(it)
                }
            }

            diffUtilExample.setOnClickListener {
                Intent(this@HomeActivity, DiffUtilExampleActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }


}