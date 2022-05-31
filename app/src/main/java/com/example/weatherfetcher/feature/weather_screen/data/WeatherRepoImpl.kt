package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.feature.weather_screen.ui.model.WeatherModel
import com.example.weatherfetcher.feature.weather_screen.ui.model.WindModel

class WeatherRepoImpl(private val weatherRemoteSource: WeatherRemoteSource) : WeatherRepo {
    override suspend fun getTemperature(selectedCity: String): WeatherModel {
        return weatherRemoteSource.getWeather(selectedCity = selectedCity).temperatureToDomain()
    }

    override suspend fun getWind(selectedCity: String): WindModel {
        return weatherRemoteSource.getWeather(selectedCity = selectedCity).windToDomain()
    }
}