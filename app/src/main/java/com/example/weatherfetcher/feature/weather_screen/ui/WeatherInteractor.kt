package com.example.weatherfetcher.feature.weather_screen.ui

import com.example.weatherfetcher.base.Either
import com.example.weatherfetcher.base.attempt
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRepo
import com.example.weatherfetcher.feature.weather_screen.ui.model.WeatherModel
import com.example.weatherfetcher.feature.weather_screen.ui.model.WindModel

class WeatherInteractor(private val weatherRepo: WeatherRepo) {

    suspend fun getWeather(selectedCity: String) : Either<Throwable, WeatherModel> {
        return attempt { weatherRepo.getTemperature(selectedCity = selectedCity) }
    }

    suspend fun getWind() : Either<Throwable, WindModel> {
        return attempt { weatherRepo.getWind() }
    }

}