package io.odbs.movie.util

import io.odbs.movie.model.SearchResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface MovieAPIService {

    @GET(".")
    fun getMovieList(
        @Query("apikey") apiKey: String = NetworkUtil.getApiKey(),
        @Query("s") searchString: String,
        @Query("page") page: Int,
    ) : Call<SearchResult>



    companion object {
        fun create(): MovieAPIService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetworkUtil.getBaseUrl()).build()

            return retrofit.create(MovieAPIService::class.java)
        }
    }



}