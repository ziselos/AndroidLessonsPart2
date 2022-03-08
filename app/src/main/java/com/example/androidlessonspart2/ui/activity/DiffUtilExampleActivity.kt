package com.example.androidlessonspart2.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlessonspart2.adapters.CityListAdapter
import com.example.androidlessonspart2.databinding.ActivityDiffUtilExampleBinding
import com.example.androidlessonspart2.models.api.weather.search.City

class DiffUtilExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiffUtilExampleBinding

    private lateinit var adapter: CityListAdapter

    private var count = 1
    private var woeid = 3
    private var cities: ArrayList<City> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiffUtilExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initLayout()
    }

    private fun initLayout() {
        binding.apply {
            addButton.setOnClickListener {
                // create a dummy city and add it to the list
                val newCity = City(title = "City ${count++}", woeid = woeid++)
                adapter.setList(addCity(newCity))
            }
        }
    }


    private fun initRecyclerView() {
        adapter = CityListAdapter { }
        binding.apply {
            recyclerView.adapter = adapter
            adapter.setList(getDummyData())
        }
    }


    private fun getDummyData(): ArrayList<City> {
        val list = arrayListOf<City>()
        list.add(City(title = "Athens", woeid = 1))
        list.add(City(title = "London", woeid = 2))

        cities = list
        return list
    }


    private fun addCity(city: City) : List<City> {
        cities.add(city)
        return cities
    }


}