package com.plaspa.weatherapp.commons.base

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.plaspa.weatherapp.R
import com.plaspa.weatherapp.commons.extension.appContext
import com.plaspa.weatherapp.commons.extension.viewContainer
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by Pedro on 13/03/2019.
 */
abstract class BaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun layoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(layoutId(), container, false)

    internal fun showSnack(@StringRes message: Int, duration: Int) =
            Snackbar.make(viewContainer, message, duration).show()

    internal fun showSnackWithAction(@StringRes message: Int, @StringRes actionText: Int, action: () -> Any) {
        val snackBar = Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(actionText) { _ -> action.invoke() }
        snackBar.setActionTextColor(ContextCompat.getColor(appContext, R.color.colorTextPrimary))
        snackBar.show()
    }

    internal fun showToast(message: String, duration: Int) = Toast.makeText(context, message, duration).show()

    fun <T> LiveData<T>.reObserve(owner: LifecycleOwner, observer: Observer<T>) {
        removeObserver(observer)
        observe(owner, observer)
    }

    protected fun showErrorHandler(isError: Boolean?) {
        isError?.let {
            showSnackWithAction(R.string.no_internet_connection, R.string.refresh, {} )
        }
    }
}