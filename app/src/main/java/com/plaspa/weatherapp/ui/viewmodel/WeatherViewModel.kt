package com.plaspa.weatherapp.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.plaspa.weatherapp.commons.utils.NetworkHandler
import com.plaspa.weatherapp.ui.repositories.WeatherRepository
import javax.inject.Inject
import android.databinding.ObservableField
import android.util.Log
import com.plaspa.weatherapp.model.Forecast
import com.plaspa.weatherapp.model.Weather

/**
 * Created by Pedro on 13/03/2019.
 */
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository, private val networkHandler: NetworkHandler) : ViewModel() {

    var showProgressObs = ObservableField<Boolean>()

    var weatherMethods: MutableLiveData<Weather> = MutableLiveData()
    var hasWeather = ObservableField<Boolean>()

    var forecastMethods: MutableLiveData<Forecast> = MutableLiveData()
    var forecastCount = ObservableField<Int>()

    var errorConnection: MutableLiveData<Boolean> = MutableLiveData()
    var errorService: MutableLiveData<Boolean> = MutableLiveData()

    init {
        showProgressObs.set(false)
        hasWeather.set(false)
        forecastCount.set(0)
    }

    fun getWeatherMethods(place: String) {
        when (networkHandler.isConnected) {
            true -> {
                weatherMethods.value = null
                showProgressObs.set(true)
                weatherRepository.getWeatherMethods(place).subscribe({ weather ->
                    hasWeather.set(true)
                    weatherMethods.value = weather
                }, this::handleError)
            }
            else -> errorConnection.value = true
        }
    }

    fun getForecastMethods(id: Int) {
        when (networkHandler.isConnected) {
            true -> {
                weatherMethods.value = null
                weatherRepository.getForecastMethods(id).subscribe({ forecast ->
                    showProgressObs.set(false)
                    forecastCount.set(forecast.cnt)
                    forecastMethods.value = forecast
                }, this::handleError)
            }
            else -> errorConnection.value = true
        }
    }

    private fun handleError(error: Throwable) {
        showProgressObs.set(false)
        Log.e(WeatherViewModel::class.java.name, error.message + error.cause)
        errorService.value = true
    }
}

