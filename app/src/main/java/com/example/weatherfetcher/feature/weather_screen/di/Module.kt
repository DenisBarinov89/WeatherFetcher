package com.example.weatherfetcher.feature.weather_screen.di

import com.example.weatherfetcher.BASE_URL
import com.example.weatherfetcher.feature.weather_screen.data.*
import com.example.weatherfetcher.feature.weather_screen.ui.WeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.ui.WeatherScreenViewModel
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val weatherScreenModule = module {
//    presenter = WeatherScreenPresenter(
//        WeatherInteractor(
//            WeatherRepoImpl(
//                WeatherRemoteSource(WeatherApiClient.getApi())
//            )
//        )
//    )

    single { OkHttpClient.Builder().build() }

    single<Retrofit> { Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(get())
        .build() }

    single { get<Retrofit>().create(WeatherApi::class.java) }

    single<WeatherRemoteSource> { WeatherRemoteSource(get()) }

    single<WeatherRepo> { WeatherRepoImpl(get()) }

    single { WeatherInteractor(get()) }

    single { WeatherScreenViewModel(get())}
}