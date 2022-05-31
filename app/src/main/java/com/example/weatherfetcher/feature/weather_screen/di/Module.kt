package com.example.weatherfetcher.feature.weather_screen.di

import com.example.weatherfetcher.BASE_URL
import com.example.weatherfetcher.feature.weather_screen.data.*
import com.example.weatherfetcher.feature.weather_screen.ui.WeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.ui.WeatherScreenViewModel
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//пакет di (DependenciesInjection) - используется библиотека Koin, описание зависимостей

val weatherScreenModule = module {

    single { OkHttpClient.Builder().build() } // собираем OkHttpClient - используется паттерн "Builder" (зачем? - кратко:
                                              // сама делает всяки штуки, по типу "закрыть соединение", "открыть соединение" и пр. )

    single<Retrofit> { Retrofit.Builder()  //собираем Retrofit для похода в интернет
        .baseUrl(BASE_URL)                 //передаем URL, куда идем
        .addConverterFactory(GsonConverterFactory.create()) //подключаем конвертер, чтобы полученная JSON-строка по запросу конвертилась в объект
        .client(get()) //подключаем http-клиент (OkhttpClient)
        .build() }

    single { get<Retrofit>().create(WeatherApi::class.java) } //собираем конечный запрос для получения JSON

    single<WeatherRemoteSource> { WeatherRemoteSource(get()) }

    single<WeatherRepo> { WeatherRepoImpl(get()) }

    single { WeatherInteractor(get()) }

    single { WeatherScreenViewModel(get())}
}