package com.plaspa.weatherapp.di

/**
 * Created by Pedro on 02/10/2018.
 */

import com.plaspa.mercadopago.di.module.ActivityModule
import com.plaspa.mercadopago.di.module.FragmentModule
import com.plaspa.mercadopago.di.module.ViewModelModule
import com.plaspa.weatherapp.WeatherApplication
import com.plaspa.weatherapp.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class), (ViewModelModule::class), (ActivityModule::class), (FragmentModule::class), (AndroidInjectionModule::class)])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: WeatherApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: WeatherApplication)
}