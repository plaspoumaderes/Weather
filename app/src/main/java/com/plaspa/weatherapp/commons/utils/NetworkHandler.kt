package com.plaspa.weatherapp.commons.utils

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton
import com.plaspa.weatherapp.commons.extension.networkState

/**
 * Created by Pedro on 13/03/2019.
 */
@Singleton
class NetworkHandler @Inject constructor(private val context: Context) {
    val isConnected get() = context.networkState?.isConnectedOrConnecting
}