package com.example.androidlessonspart2.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.example.androidlessonspart2.adapters.CityListAdapter
import com.example.androidlessonspart2.api.APIInterface
import com.example.androidlessonspart2.api.WeatherAPIClient
import com.example.androidlessonspart2.databinding.ActivityWeatherCitiesBinding
import com.example.androidlessonspart2.models.api.weather.search.City
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherCitiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherCitiesBinding
    private lateinit var adapter: CityListAdapter
    private var apiInterface: APIInterface? = null
    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherCitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CityListAdapter { cityWeather ->
            Intent(this@WeatherCitiesActivity, WeatherForecastActivity::class.java).also {
                it.putExtra("city", cityWeather)
                startActivity(it)
            }
        }
        binding.apply {
            recyclerView.adapter = adapter
            citySearch.onTextChanged {
                fetchCities(it)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchCities(query: String) {
        apiInterface = WeatherAPIClient.weatherClient?.create(APIInterface::class.java)
        binding.loader.visibility = View.VISIBLE
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = apiInterface?.getSearchLocationWeather(query)
            withContext(Dispatchers.Main) {
                // hide loader
                binding.loader.visibility = View.GONE
                if (response?.isSuccessful == true) {
                    // update the UI
                    val cityResults = response.body() as ArrayList<City>? ?: arrayListOf()
                    adapter.setList(cityResults)
                    adapter.notifyDataSetChanged()
                } else {
                    // error handling
                    Log.d("Weather Forecast Error", response?.errorBody().toString())
                }
            }
        }
    }
}

fun AppCompatEditText.onTextChanged(textChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            textChanged(text.toString())
        }
    })
}