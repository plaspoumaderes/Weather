package com.plaspa.weatherapp.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.plaspa.weatherapp.R
import com.plaspa.weatherapp.commons.Constants
import com.plaspa.weatherapp.commons.base.BaseFragment
import com.plaspa.weatherapp.commons.extension.loadFromUrl
import com.plaspa.weatherapp.commons.utils.UrlUtil
import com.plaspa.weatherapp.databinding.FragmentMainBinding
import com.plaspa.weatherapp.model.Forecast
import com.plaspa.weatherapp.model.Weather
import com.plaspa.weatherapp.ui.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlin.properties.Delegates

/**
 * A placeholder fragment containing a simple view.
 */
class MainFragment : BaseFragment(), AdapterView.OnItemSelectedListener {

    override fun layoutId(): Int = R.layout.fragment_main

    private lateinit var viewModel: WeatherViewModel

    private var mBinding: FragmentMainBinding by Delegates.notNull()

    private lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        mBinding.viewModel = viewModel
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fr_main_country_spinner.onItemSelectedListener = this

        activity?.let {
            sharedPreference = it.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)
            if (sharedPreference.contains(Constants.SHARED_COUNTRY)) {
                fr_main_country_spinner.setSelection(sharedPreference.getInt(Constants.SHARED_POSITION, 0))
                viewModel.getWeatherMethods(sharedPreference.getString(Constants.SHARED_COUNTRY, ""))
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.weatherMethods.reObserve(this, Observer { loadWeatherMethod(it) })
        viewModel.forecastMethods.reObserve(this, Observer { loadForecastMethod(it) })
        viewModel.errorConnection.reObserve(this, Observer { showErrorHandler(it) })
        viewModel.errorService.reObserve(this, Observer { showToast(getString(R.string.errorService), Toast.LENGTH_SHORT) })
    }

    private fun loadWeatherMethod(weather: Weather?) {
        weather?.let {
            fr_main_temp_txt.text = "${it.main.temp} °C"
            fr_main_temp_min_max_txt.text = "${it.main.temp_min} / ${it.main.temp_max}"
            if (it.weather.isNotEmpty()) {
                val weather = it.weather[0]
                val url = UrlUtil.createIconUrl(weather.icon)
                fr_main_temp_img.loadFromUrl(url)
                fr_main_weather_desc.text = weather.description
            }
            viewModel.getForecastMethods(it.id)
        }
    }


    private fun loadForecastMethod(forecast: Forecast?) {
        forecast?.let {
            it.list.forEachIndexed { index, weather ->
                loadForecastItem(index, weather)
            }
        }
    }

    private fun loadForecastItem(position: Int, weather: Weather) {
        val url = if (weather.weather.isNotEmpty()) UrlUtil.createIconUrl(weather.weather[0].icon) else ""
        when (position) {
            0 -> {
                first_day_txt.text = "${weather.main.temp.toInt()}°C"
                first_day_img.loadFromUrl(url)
            }
            1 -> {
                second_day_txt.text = "${weather.main.temp.toInt()}°C"
                second_day_img.loadFromUrl(url)
            }
            2 -> {
                third_day_txt.text = "${weather.main.temp.toInt()}°C"
                third_day_img.loadFromUrl(url)
            }
            3 -> {
                fourth_day_txt.text = "${weather.main.temp.toInt()}°C"
                fourth_day_img.loadFromUrl(url)
            }
            4 -> {
                five_day_txt.text = "${weather.main.temp.toInt()}°C"
                five_day_img.loadFromUrl(url)
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        context?.let {
            val country = it.resources.getStringArray(R.array.city_array)[position]
            viewModel.getWeatherMethods(country)
            var editor: SharedPreferences.Editor = sharedPreference.edit()
            editor.putString(Constants.SHARED_COUNTRY, country)
            editor.putInt(Constants.SHARED_POSITION, position)
            editor.commit()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}
