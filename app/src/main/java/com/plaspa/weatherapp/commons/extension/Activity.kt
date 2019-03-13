package com.plaspa.weatherapp.commons.extension

/**
 * Created by Pedro on 13/03/2019.
 */
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.replaceFragment(withAnimation: Boolean, container: Int, fragment: Fragment, args: Bundle?,
                                      addBackStack: Boolean) {
    supportFragmentManager.inTransaction {
        when {
            args != null -> fragment.arguments = args
        }
        if (addBackStack)  addToBackStack(fragment.tag+Math.random())
        replace(container, fragment)
    }
}