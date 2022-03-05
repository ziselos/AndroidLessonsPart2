package com.example.androidlessonspart2.models.api.weather.search

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    val title: String,
    val woeid: Int
) : Parcelable