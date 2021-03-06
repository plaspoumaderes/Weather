package com.plaspa.weatherapp.ui.repositories

import com.plaspa.weatherapp.commons.Constants
import com.plaspa.weatherapp.model.Forecast
import com.plaspa.weatherapp.model.Weather
import com.plaspa.weatherapp.ui.services.WeatherServices
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import java.net.HttpURLConnection
import javax.inject.Inject

/**
 * Created by Pedro on 13/03/2019.
 */
class WeatherRepository @Inject constructor(retrofit: Retrofit) {

    private val weatherApi by lazy { retrofit.create(WeatherServices::class.java) }

    fun getWeatherMethods(place: String): Observable<Weather> {
        return weatherApi.getWeather(Constants.API_KEY, place, Constants.CENTIGRADO)
                .concatMap { httpValidation(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getForecastMethods(id: Int): Observable<Forecast> {
        return weatherApi.getForecast(Constants.API_KEY, id, 5, Constants.CENTIGRADO)
                .concatMap { httpValidation(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


    private fun <T> httpValidation(response: Response<T>): Observable<T> {
        return Observable.fromCallable {
            if (response.isSuccessful) {
                response.body()?.let {
                    it
                }
            } else throw getExceptionHandler(response.code(), response.errorBody())
        }
    }

    private fun getExceptionHandler(code: Int, errorBody: ResponseBody?): Throwable {
        when (code) {
            HttpURLConnection.HTTP_NOT_FOUND -> throw Exception("Service request not found")
            HttpURLConnection.HTTP_UNAUTHORIZED -> throw Exception("User not authorized to access services")
            HttpURLConnection.HTTP_CONFLICT -> throw Exception("User token auth still available")
            else -> throw Exception("Unknown error")
        }
    }
}
