package io.odbs.movie.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie(


    @SerializedName("Actors")
    @Expose
    var actors: String,

    @SerializedName("Awards")
    @Expose
    var awards: String,

    @SerializedName("BoxOffice")
    @Expose
    var boxOffice: String,

    @SerializedName("Country")
    @Expose
    var country: String,

    @SerializedName("DVD")
    @Expose
    var DVD: String,

    @SerializedName("Director")
    @Expose
    var director: String,

    @SerializedName("Genre")
    @Expose
    var genre: String,

    @SerializedName("Language")
    @Expose
    var language: String,

    @SerializedName("Metascore")
    @Expose
    var metascore: String,

    @SerializedName("Plot")
    @Expose
    var plot: String,

    @SerializedName("Poster")
    @Expose
    var poster: String,

    @SerializedName("Production")
    @Expose
    var production: String,

    @SerializedName("Rated")
    @Expose
    var rated: String,

    @SerializedName("Ratings")
    @Expose
    var ratings: List<Rating>,

    @SerializedName("Released")
    @Expose
    var released: String,

    @SerializedName("Response")
    @Expose
    var response: String,

    @SerializedName("Runtime")
    @Expose
    var runtime: String,

    @SerializedName("Title")
    @Expose
    var title: String,

    @SerializedName("Type")
    @Expose
    var type: String,

    @SerializedName("Website")
    @Expose
    var website: String,

    @SerializedName("Writer")
    @Expose
    var writer: String,

    @SerializedName("Year")
    @Expose
    var year: String,

    @SerializedName("imdbID")
    @Expose
    var imdbID: String,

    @SerializedName("imdbRating")
    @Expose
    var imdbRating: String,

    @SerializedName("imdbVotes")
    @Expose
    var imdbVotes: String
)