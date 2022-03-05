package com.example.androidlessonspart2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlessonspart2.R.layout
import com.example.androidlessonspart2.models.api.weather.search.City
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.holder_city.*

class CityListAdapter(private val onCityClickListener: (City) -> Unit) :
    RecyclerView.Adapter<CityListAdapter.CitySearchViewHolder>() {

    private val cityList: ArrayList<City> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitySearchViewHolder {
        return CitySearchViewHolder(
            LayoutInflater.from(parent.context).inflate(layout.holder_city, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CitySearchViewHolder, position: Int) {
        holder.bind(cityList[position], onCityClickListener)
    }

    override fun getItemCount(): Int = cityList.size

    fun setList(list: ArrayList<City>) {
        cityList.clear()
        cityList.addAll(list)
    }

    class CitySearchViewHolder(
        override val containerView: View
    ) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(city: City, onCityClickListener: (City) -> Unit) {
            cityName.setOnClickListener {
                onCityClickListener.invoke(city)
            }
            cityName.text = city.title
        }
    }
}