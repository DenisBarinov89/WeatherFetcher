package com.example.weatherfetcher.feature.weather_screen.ui

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherfetcher.base.BaseViewModel
import com.example.weatherfetcher.base.Event
import com.example.weatherfetcher.feature.weather_screen.ui.model.WeatherModel
import kotlinx.coroutines.launch
import java.lang.Exception

class WeatherScreenViewModel(val interactor: WeatherInteractor) : BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState(isLoading = false, temperature = "", windSpeed = "", windDeg = "")

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.onButtonClicked -> {
                viewModelScope.launch {
                    interactor.getWeather(selectedCity = event.selectedCity).fold(
                        onError = {
                            processDataEvent(DataEvent.OnWeatherFetchFailure(error = it))
                        },
                        onSuccess = {
                            processDataEvent(DataEvent.OnWeatherFetchSucceed(temperature = "Temperature: ${it.temperature} C"))
                        }
                    )
                }
                return previousState.copy(isLoading = true)
            }
            is UiEvent.onButtonGetWindClicked -> {
                viewModelScope.launch {
                    interactor.getWind().fold(
                        onError = {
                            processDataEvent(DataEvent.OnWeatherFetchFailure(error = it))
                        },
                        onSuccess = {
                            processDataEvent(DataEvent.OnWindFetchSucceed(windDeg = "Wind degree: ${it.windDeg}", windSpeed = "Speed: ${it.windSpeed}"))
                        }
                    )
                }
                return previousState.copy(isLoading = true)
            }
            is DataEvent.OnWeatherFetchSucceed -> {
                return previousState.copy(isLoading = false, temperature = event.temperature)
            }
            is DataEvent.OnWindFetchSucceed -> {
                return previousState.copy(isLoading = false, windDeg = event.windDeg, windSpeed = event.windSpeed)
            }
            else -> return null
        }
    }

}