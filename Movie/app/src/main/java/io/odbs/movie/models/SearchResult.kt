package io.odbs.movie.models

import com.google.gson.annotations.SerializedName

data class SearchResult(

    @SerializedName("Response")
    val response: String,

    @SerializedName("Search")
    val search: List<Search>,

    @SerializedName("totalResults")
    val totalResults: String
)