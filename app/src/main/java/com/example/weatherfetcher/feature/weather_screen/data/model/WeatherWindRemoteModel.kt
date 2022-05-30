package com.example.weatherfetcher.feature.weather_screen.data.model

import com.google.gson.annotations.SerializedName

data class WeatherWindRemoteModel(
    @SerializedName("speed")
    val windSpeed: String,

    @SerializedName("deg")
    val windDeg: String

) {
}