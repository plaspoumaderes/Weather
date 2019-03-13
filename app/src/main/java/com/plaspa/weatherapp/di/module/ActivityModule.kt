package com.plaspa.mercadopago.di.module

import com.plaspa.weatherapp.di.annotations.ActivityScope
import com.plaspa.weatherapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule{
    @ContributesAndroidInjector(modules = [(FragmentModule::class)])
    @ActivityScope
    abstract fun contributePayActivity(): MainActivity
}