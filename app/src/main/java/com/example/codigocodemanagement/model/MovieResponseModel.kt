package com.example.codigocodemanagement.model

import android.os.Parcelable
import com.example.codigocodemanagement.room.entity.MovieEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponseModel(
    val results: List<MovieEntity>
):Parcelable

/*@Parcelize
data class MovieModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("genres")
    val genres: List<GenreModel>,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryModel>,

    @SerializedName("vote_average")
    val voteAverage: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("runtime")
    val runtime: Int,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<LanguageModel>,

    @SerializedName("vote_count")
    val voteCount: String,

    ):Parcelable*/

@Parcelize
data class GenreModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,
):Parcelable

@Parcelize
data class ProductionCountryModel(
    @SerializedName("iso_3166_1")
    val iso: String,

    @SerializedName("name")
    val name: String,
):Parcelable

@Parcelize
data class LanguageModel(
    @SerializedName("name")
    val name: String
):Parcelable
