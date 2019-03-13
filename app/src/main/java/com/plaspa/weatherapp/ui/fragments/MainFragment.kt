package com.plaspa.weatherapp.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.plaspa.weatherapp.R
import com.plaspa.weatherapp.commons.base.BaseFragment
import com.plaspa.weatherapp.databinding.FragmentMainBinding
import com.plaspa.weatherapp.model.WeatherResponse
import com.plaspa.weatherapp.ui.viewmodel.WeatherViewModel
import kotlin.properties.Delegates

/**
 * A placeholder fragment containing a simple view.
 */
class MainFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.fragment_main

    private lateinit var viewModel: WeatherViewModel

    private var mBinding: FragmentMainBinding by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)
        viewModel.getWeatherMethods("London")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater,layoutId(), container, false)
        mBinding.viewModel = viewModel
        val rootView = mBinding.root

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.weatherMethods.reObserve(this, Observer { loadWeatherMethod(it) })
        viewModel.errorConnection.reObserve(this, Observer { showErrorHandler(it) })
        viewModel.errorService.reObserve(this, Observer { showToast(getString(R.string.errorService), Toast.LENGTH_SHORT) })
    }

    private fun loadWeatherMethod(weatherResponse: WeatherResponse?) {
        Log.i("Weather", weatherResponse.toString())

    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}
