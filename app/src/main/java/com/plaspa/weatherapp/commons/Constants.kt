package com.plaspa.weatherapp.commons

/**
 * Created by Pedro on 13/03/2019.
 */
class Constants {
    companion object {

        const val API_KEY :String = "450956f8a7d9dcb6b4d1865a185733c8"
        const val END_POINT : String = "http://api.openweathermap.org/"

        const val END_POINT_BASE : String = "data/2.5/"
        const val WEATHER : String = "weather"
        const val FORECAST : String = "forecast"

        const val QUERY_API_KEY : String = "APPID"
        const val QUERY_Q : String = "q"
        const val QUERY_ID : String = "id"
        const val QUERY_LIMIT : String = "cnt"
        const val QUERY_UNITS : String = "units"

        const val CENTIGRADO : String = "metric"

        const val SHARED_PREF : String = "sharedPreference"
        const val SHARED_COUNTRY : String = "sharedPreference_country"
        const val SHARED_POSITION : String = "sharedPreference_position"
    }
}

