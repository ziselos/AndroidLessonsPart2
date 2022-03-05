package com.example.androidlessonspart2.api

import com.example.androidlessonspart2.models.api.NewsByCategoryResponse
import com.example.androidlessonspart2.models.api.uiModels.disney.DisneyCharactersModel
import com.example.androidlessonspart2.models.api.weather.LocationWeatherResponse
import com.example.androidlessonspart2.models.api.weather.search.City
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    // fetch news
    @GET("news")
    suspend fun getNewsByCategory(@Query("category") category: String): Response<NewsByCategoryResponse>

    // fetch disney characters
    @GET("/characters")
    suspend fun getDisneyCharacters(): Response<DisneyCharactersModel>

    // fetch weather forecast
    @GET("api/location/{woeid}")
    suspend fun getLocationWeather(@Path("woeid") woeid: String): Response<LocationWeatherResponse>

    // search weather forecast
    @GET("api/location/search")
    suspend fun getSearchLocationWeather(@Query("query") query: String): Response<List<City>>
}

