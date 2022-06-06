package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.feature.weather_screen.data.model.WeatherRemoteModel

class WeatherRemoteSource(private val api : WeatherApi) {

    suspend fun getWeather(selectedCity: String = "Moscow"): WeatherRemoteModel {
        return api.getWeather(selectedCity)
    }
}