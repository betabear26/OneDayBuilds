package io.odbs.movie.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResult(

    @SerializedName("Response")
    @Expose
    var response: String,

    @SerializedName("Search")
    @Expose
    var search: List<Search>,

    @SerializedName("totalResults")
    @Expose
    var totalResults: String
)