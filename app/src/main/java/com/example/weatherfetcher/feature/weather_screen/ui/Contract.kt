package com.example.weatherfetcher.feature.weather_screen.ui

import com.example.weatherfetcher.base.Event

data class ViewState(
    val isLoading: Boolean,
    val temperature: String,
    val windDeg: String,
    val windSpeed: String
)

sealed class UiEvent() : Event {
    data class onButtonClicked(val selectedCity: String) : UiEvent()
    object onButtonGetWindClicked : UiEvent()
}

sealed class DataEvent : Event {
    data class OnWeatherFetchSucceed(val temperature: String) : DataEvent()
    data class OnWindFetchSucceed(val windDeg: String, val windSpeed: String) : DataEvent()
    data class OnWeatherFetchFailure(val error: Throwable) : DataEvent()
}