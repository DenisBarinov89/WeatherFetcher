package com.example.weatherfetcher.presentation.temperature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import com.example.weatherfetcher.CITIES_LIST
import com.example.weatherfetcher.R
import com.example.weatherfetcher.feature.weather_screen.ui.UiEvent
import com.example.weatherfetcher.feature.weather_screen.ui.ViewState
import com.example.weatherfetcher.feature.weather_screen.ui.WeatherScreenViewModel
import com.example.weatherfetcher.presentation.wind.WindActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherScreenViewModel by viewModel() //инициализация ViewModel
    //инициализация переменных элементов экрана
    private val fabWeather: FloatingActionButton by lazy { findViewById(R.id.fabWeatherFetch) }
    private val textViewHello: TextView by lazy { findViewById(R.id.tvHello) }
    private val progressBar: ProgressBar by lazy { findViewById(R.id.progressBar) }
    private val toWindScreen: Button by lazy {findViewById(R.id.btnGetWindActivity)}
    private val spinnerSelectCity: Spinner by lazy { findViewById(R.id.spSelectCityMA) }
    private val arrayAdapter: ArrayAdapter<String> by lazy {ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, CITIES_LIST)}
    private var selectedCity: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerSelectCity.adapter = arrayAdapter
        spinnerSelectCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedCity = CITIES_LIST[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        viewModel.viewState.observe(this, ::render)  //ViewModel обсервер, наблюдает за изменениями данных
        fabWeather.setOnClickListener {
            viewModel.processUIEvent(UiEvent.onButtonClicked(selectedCity.toString()))
        }

        toWindScreen.setOnClickListener {
            val intent = Intent(this, WindActivity::class.java)
            startActivity(intent)
        }
    }

    private fun render(viewState: ViewState) {
        progressBar.isVisible = viewState.isLoading
        textViewHello.text = viewState.temperature
    }
}