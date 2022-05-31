package com.example.weatherfetcher.presentation.wind

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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class WindActivity : AppCompatActivity() {
    private val viewModel: WeatherScreenViewModel by viewModel() //инициализация ViewModel

    //инициализация переменных элементов экрана
    private val fabWind: FloatingActionButton by lazy { findViewById(R.id.btnGetWind) }
    private val textViewWind: TextView by lazy { findViewById(R.id.tvWind) }
    private val progressBar: ProgressBar by lazy { findViewById(R.id.progressBarWind) }
    private val spinnerSelectCity: Spinner by lazy { findViewById(R.id.spSelectCityWA) }
    private val arrayAdapter: ArrayAdapter<String> by lazy { ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, CITIES_LIST) }
    private var selectedCity: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wind)
        spinnerSelectCity.adapter = arrayAdapter
        spinnerSelectCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedCity = CITIES_LIST[p2]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        viewModel.viewState.observe(this, ::render)  //ViewModel обсервер, наблюдает за изменениями данных

        fabWind.setOnClickListener {
            viewModel.processUIEvent(UiEvent.onButtonGetWindClicked(selectedCity.toString()))
        }
    }

    private fun render(viewState: ViewState) {
        progressBar.isVisible = viewState.isLoading
        val windInfo = "${viewState.windDeg} ${viewState.windSpeed}"
        textViewWind.text = windInfo
    }
}