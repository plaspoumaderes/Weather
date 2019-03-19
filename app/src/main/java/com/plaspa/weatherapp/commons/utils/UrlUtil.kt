package com.plaspa.weatherapp.commons.utils

class UrlUtil {
    companion object {
        fun createIconUrl(icon: String): String {
            return "http://openweathermap.org/img/w/$icon.png"
        }
    }
}