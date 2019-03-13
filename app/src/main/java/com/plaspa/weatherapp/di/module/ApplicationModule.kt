package com.plaspa.weatherapp.di.module

import com.plaspa.weatherapp.commons.Constants
import com.plaspa.weatherapp.WeatherApplication
import com.plaspa.weatherapp.ui.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.END_POINT)
                .client(createClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideApplicationContext(application: WeatherApplication) = application.applicationContext

    @Provides
    @Singleton
    fun providePayRepository(retrofit: Retrofit): WeatherRepository = WeatherRepository(retrofit)

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }
}