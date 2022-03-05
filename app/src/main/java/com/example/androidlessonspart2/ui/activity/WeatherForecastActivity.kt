package com.example.androidlessonspart2.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlessonspart2.R
import com.example.androidlessonspart2.adapters.CityWeatherAdapter
import com.example.androidlessonspart2.api.APIInterface
import com.example.androidlessonspart2.api.WeatherAPIClient
import com.example.androidlessonspart2.databinding.ActivityWeatherForecastBinding
import com.example.androidlessonspart2.models.api.weather.ConsolidatedWeather
import com.example.androidlessonspart2.models.api.weather.search.City
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherForecastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherForecastBinding
    private lateinit var adapter: CityWeatherAdapter
    private lateinit var city: City
    private var apiInterface: APIInterface? = null
    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        city = intent.getParcelableExtra<City>("city") as City
        initCityTitle()
        initRecyclerView()
        fetchWeatherData(city.woeid.toString())
    }

    private fun initCityTitle() {
        binding.cityTitle.text = getString(R.string.city_weather_forecast_title, city.title)
    }

    private fun initRecyclerView() {
        adapter = CityWeatherAdapter()
        binding.recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchWeatherData(cityId: String) {
        apiInterface = WeatherAPIClient.weatherClient?.create(APIInterface::class.java)
        binding.loader.visibility = View.VISIBLE
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = apiInterface?.getLocationWeather(cityId)
            withContext(Dispatchers.Main) {
                // hide loader
                binding.loader.visibility = View.GONE
                if (response?.isSuccessful == true) {
                    // update the UI
                    adapter.setList(response.body()?.consolidated_weather as ArrayList<ConsolidatedWeather>)
                    adapter.notifyDataSetChanged()
                    Log.d(
                        "Weather Forecast",
                        response.body()?.consolidated_weather?.size.toString()
                    )
                } else {
                    // error handling
                    Log.d("Weather Forecast Error", response?.errorBody().toString())
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}