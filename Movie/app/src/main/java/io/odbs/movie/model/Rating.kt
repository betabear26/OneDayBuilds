package io.odbs.movie.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rating(

    @SerializedName("Source")
    @Expose
    var source: String,

    @SerializedName("Value")
    @Expose
    var value: String
)