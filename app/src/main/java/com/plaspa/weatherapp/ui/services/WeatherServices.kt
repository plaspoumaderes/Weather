package com.plaspa.weatherapp.ui.services

import com.plaspa.weatherapp.commons.Constants
import com.plaspa.weatherapp.model.Forecast
import com.plaspa.weatherapp.model.Weather

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Pedro on 13/03/2019.
 */
interface WeatherServices {
    @GET(Constants.END_POINT_BASE + Constants.WEATHER)
    fun getWeather(@Query(Constants.QUERY_API_KEY) api_key: String,
                   @Query(Constants.QUERY_Q) place: String,
                   @Query(Constants.QUERY_UNITS) unit: String
    ): Observable<Response<Weather>>

    @GET(Constants.END_POINT_BASE + Constants.FORECAST)
    fun getForecast(@Query(Constants.QUERY_API_KEY) api_key: String,
                    @Query(Constants.QUERY_ID) place: Int,
                    @Query(Constants.QUERY_LIMIT) limit: Int,
                    @Query(Constants.QUERY_UNITS) unit: String
    ): Observable<Response<Forecast>>
}