package com.plaspa.weatherapp.model

/**
 * Created by Pedro on 13/03/2019.
 */
class WeatherResponse(
        var coord: Coordenadas,
        var weather: List<WeatherState>,
        var base: String,
        var main: WeatherMain,
        var visibility: Int,
        var wind: Wind,
        var dt: Int,
        var id: Int,
        var name: String,
        var cod: Int
)