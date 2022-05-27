package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.feature.weather_screen.data.model.WeatherRemoteModel
import retrofit2.Response

class WeatherRemoteSource(private val api : WeatherApi) {

    //TODO add querry
    suspend fun getWeather(): WeatherRemoteModel {
        return api.getWeather("Moscow")
    }

}