package com.plaspa.weatherapp.commons.extension

/**
 * Created by Pedro on 13/03/2019.
 */
import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.plaspa.weatherapp.commons.base.BaseActivity
import com.plaspa.weatherapp.commons.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
        beginTransaction().func().commit()

val BaseFragment.viewContainer: View get() = (activity as BaseActivity).fragment_container
val BaseFragment.appContext: Context get() = activity?.applicationContext!!