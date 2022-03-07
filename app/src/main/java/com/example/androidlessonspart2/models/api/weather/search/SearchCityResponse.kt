package com.example.androidlessonspart2.models.api.weather.search

class SearchCityResponse : ArrayList<SearchCityResponseItem>() {


    fun mapToCityList(): List<City> {
        return this.map {
            it.mapToCity()
        }
    }

}