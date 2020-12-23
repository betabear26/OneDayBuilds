package io.odbs.movie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.odbs.movie.model.SearchResult
import io.odbs.movie.util.MovieAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultViewModel: ViewModel() {

    private var search : MutableLiveData<SearchResult> = MutableLiveData()

    private val movieAPIService by lazy {
        MovieAPIService.create()
    }

    val observer get() = search

    fun callSearchApi(searchString: String, pageNumber: Int){
        val call = movieAPIService.getMovieList(searchString = searchString, page = pageNumber)
        call.enqueue(object : Callback<SearchResult>{
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                search.postValue(response.body())
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                search.postValue(null)
            }

        })
    }

}