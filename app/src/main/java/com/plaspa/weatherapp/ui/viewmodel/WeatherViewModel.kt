package com.plaspa.weatherapp.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.plaspa.weatherapp.commons.utils.NetworkHandler
import com.plaspa.weatherapp.ui.repositories.WeatherRepository
import javax.inject.Inject
import android.databinding.ObservableField
import android.util.Log
import com.plaspa.weatherapp.model.WeatherResponse

/**
 * Created by Pedro on 13/03/2019.
 */
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository, private val networkHandler: NetworkHandler) : ViewModel() {

    var showProgressObs = ObservableField<Boolean>()
    var hasData = ObservableField<Boolean>()

    var weatherMethods: MutableLiveData<WeatherResponse> = MutableLiveData()

    var errorConnection: MutableLiveData<Boolean> = MutableLiveData()
    var errorService: MutableLiveData<Boolean> = MutableLiveData()

    init {
        showProgressObs.set(false)
        hasData.set(false)
    }

    fun getWeatherMethods(place: String) {
        when (networkHandler.isConnected) {
            true -> {
                weatherMethods.value = null
                showProgressObs.set(true)
                weatherRepository.getWeatherMethods(place).subscribe({ weather ->
                    showProgressObs.set(false)
                    hasData.set(true)
                    weatherMethods.value = weather
                }, this::handleError)
            }
            else -> errorConnection.value = true
        }
    }

    private fun handleError(error: Throwable) {
        showProgressObs.set(false)
        hasData.set(false)
        Log.e(WeatherViewModel::class.java.name, error.message + error.cause)
        errorService.value = true
    }
}

