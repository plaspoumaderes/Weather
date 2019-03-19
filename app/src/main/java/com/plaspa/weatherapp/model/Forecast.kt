package com.plaspa.weatherapp.model

/**
 * Created by Pedro on 13/03/2019.
 */
class Forecast(
        var list: List<Weather>,
        var base: String,
        var main: WeatherMain,
        var city: City,
        var message: Double,
        var cnt: Int,
        var cod: Int
)