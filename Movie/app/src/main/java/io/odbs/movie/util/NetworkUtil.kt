package io.odbs.movie.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkUtil {


    //Checking if phone has an active network. Does not verify if the device is actually "online",
    //i.e. connected to the internet
    companion object {
        @Suppress("deprecation")
        fun isInternetConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val activeNetwork = cm.activeNetwork
                if (activeNetwork != null) {
                    val capabilities = cm.getNetworkCapabilities(activeNetwork)
                    return capabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI
                    )
                }
                return false
            } else if (cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected) {
                return true
            }

            return false
        }
    }


}