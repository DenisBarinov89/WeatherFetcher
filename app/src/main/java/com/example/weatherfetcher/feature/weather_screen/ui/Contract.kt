package com.example.weatherfetcher.feature.weather_screen.ui

import com.example.weatherfetcher.base.Event

data class ViewState(
    val isLoading: Boolean,
    val temperature: String
)

sealed class UiEvent() : Event {
    object onButtonClicked : UiEvent()
}

sealed class DataEvent: Event {
    data class OnWeatherFetchSucceed(val temperature: String): DataEvent()
    data class OnWeatherFetchFailure(val error: Throwable): DataEvent()
}