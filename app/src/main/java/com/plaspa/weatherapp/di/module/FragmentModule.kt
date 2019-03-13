package com.plaspa.mercadopago.di.module

import com.plaspa.mercadopago.di.annotations.FragmentScope
import com.plaspa.weatherapp.ui.fragments.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule{

    @ContributesAndroidInjector
    @FragmentScope
    abstract
    fun contributeMainFragment(): MainFragment

}