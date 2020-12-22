package io.odbs.movie.models

import com.google.gson.annotations.SerializedName

data class Rating(

    @SerializedName("Source")
    val source: String,

    @SerializedName("Value")
    val value: String
)