package com.practice.samplekotlinapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

/**
 * This is singleton class object internally it will create class for this object with same name.
 */
object Utility {
    fun ProgressBar.show() {
        visibility = View.VISIBLE
    }

    fun ProgressBar.hide() {
        visibility = View.GONE
    }

    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /**
     * check is network connectivity is available or not.
     *
     * @return boolean value "true" if network available.
     */
    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return result
    }
}
