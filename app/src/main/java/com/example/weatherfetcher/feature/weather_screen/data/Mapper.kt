package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.feature.weather_screen.data.model.WeatherRemoteModel
import com.example.weatherfetcher.feature.weather_screen.ui.model.WeatherModel
import com.example.weatherfetcher.feature.weather_screen.ui.model.WindModel

fun WeatherRemoteModel.temperatureToDomain() = WeatherModel(
    temperature = this.main.temperature,
)

fun WeatherRemoteModel.windToDomain() = WindModel(
    windDeg = this.wind.windDeg,
    windSpeed = this.wind.windSpeed
)