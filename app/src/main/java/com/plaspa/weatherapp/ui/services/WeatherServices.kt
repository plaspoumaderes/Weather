package com.plaspa.weatherapp.ui.services

import com.plaspa.weatherapp.commons.Constants
import com.plaspa.weatherapp.model.WeatherResponse

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Pedro on 13/03/2019.
 */
interface WeatherServices {
    @GET(Constants.GET_WEATHER)
    fun getWeatherMethods(@Query(Constants.QUERY_API_KEY) api_key: String,
                          @Query(Constants.QUERY_Q) place: String
    ): Observable<Response<WeatherResponse>>
}