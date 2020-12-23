package io.odbs.movie.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Search(

    @SerializedName("Poster")
    @Expose
    var poster: String,

    @SerializedName("Title")
    @Expose
    var title: String,

    @SerializedName("Type")
    @Expose
    var type: String,

    @SerializedName("Year")
    @Expose
    var year: String,

    @SerializedName("imdbID")
    @Expose
    var imdbID: String
)