package com.plaspa.weatherapp.commons.extension

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by Pedro on 13/03/2019.
 */
val Context.networkState: NetworkInfo? @SuppressLint("MissingPermission")
get() =
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo