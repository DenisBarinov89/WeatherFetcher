package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.feature.weather_screen.ui.model.WeatherModel
import com.example.weatherfetcher.feature.weather_screen.ui.model.WindModel

interface WeatherRepo {

    suspend fun getTemperature(selectedCity: String) : WeatherModel

    suspend fun getWind(selectedCity: String) : WindModel
}