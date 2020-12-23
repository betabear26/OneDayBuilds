package io.odbs.movie.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkUtil {




    //Checking if phone has an active network. Does not verify if the device is actually "online",
    //i.e. connected to the internet
    companion object {

        private const val API_KEY: String = "8149c046"

        fun getApiKey() = API_KEY

        fun getBaseUrl() : String{
            return "http://www.omdbapi.com/"
        }

        fun getSearchUrl(searchString: String, pageNumber: Int): String{
            return "http://www.omdbapi.com/?apikey=$API_KEY&s=$searchString&page=$pageNumber"
        }

        fun getMovieDetailUrl(imdbId: String): String {
            return "http://www.omdbapi.com/?i=$imdbId&apikey=$API_KEY"
        }


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