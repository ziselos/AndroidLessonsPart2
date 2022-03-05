package com.example.androidlessonspart2.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.androidlessonspart2.R
import com.example.androidlessonspart2.models.api.weather.ConsolidatedWeather
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.holder_weather_day.*
import java.util.*
import kotlin.math.round

class CityWeatherAdapter : RecyclerView.Adapter<CityWeatherAdapter.CityViewHolder>() {

    private val consolidatedWeatherList = arrayListOf<ConsolidatedWeather>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.holder_weather_day, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(consolidatedWeatherList[position])
    }

    override fun getItemCount(): Int = consolidatedWeatherList.size

    fun setList(list: ArrayList<ConsolidatedWeather>) {
        consolidatedWeatherList.clear()
        consolidatedWeatherList.addAll(list)
    }

    class CityViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(model: ConsolidatedWeather) {
            rootView.setBackgroundColor(getRandomColor())
            day.text = model.applicable_date
            currentTemp.text = model.the_temp.round(2).toString()
            minTemp.text = model.min_temp.round(2).toString()
            maxTemp.text = model.max_temp.round(2).toString()
            weatherImageView.loadUrl(model.getWeatherIcon())
        }

        private fun getRandomColor(): Int {
            val rnd = Random()
            return Color.argb(200, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        }
    }
}

fun ImageView.loadUrl(url: String) {
    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry { add(SvgDecoder(this@loadUrl.context)) }
        .build()

    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .crossfade(500)
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)
        .data(url)
        .target(this)
        .build()

    imageLoader.enqueue(request)
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}