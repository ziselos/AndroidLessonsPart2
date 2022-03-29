package com.example.androidlessonspart2.models.api.weather.search

data class SearchCityResponseItem(
    val latt_long: String,
    val location_type: String,
    val title: String,
    val woeid: Int
) {
    fun mapToCity(): City {
        return City(title = this.title, woeid = this.woeid)
    }
}