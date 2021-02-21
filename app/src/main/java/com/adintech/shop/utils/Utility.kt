package com.practice.samplekotlinapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import java.io.IOException
import java.nio.charset.Charset


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

    fun loadJSONFromAsset(context: Context, jsonFileName: String): String {
        val json: String?
        try {
            val inputStream = context.assets.open(jsonFileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
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
