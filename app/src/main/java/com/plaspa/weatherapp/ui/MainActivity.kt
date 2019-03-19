package com.plaspa.weatherapp.ui

import android.os.Bundle
import com.plaspa.weatherapp.R
import com.plaspa.weatherapp.commons.base.BaseActivity
import com.plaspa.weatherapp.commons.extension.replaceFragment
import com.plaspa.weatherapp.ui.fragments.MainFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initializeFragment() {
        replaceFragment(R.id.fragment_container, MainFragment.newInstance(), Bundle(), true)
    }
}
