package com.example.codigocodemanagement.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.codigocodemanagement.model.GenreModel
import com.example.codigocodemanagement.model.LanguageModel
import com.example.codigocodemanagement.model.ProductionCountryModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tbl_movie")
@Parcelize
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @ColumnInfo(name = "genres")
    @SerializedName("genres")
    val genres: List<GenreModel>?,

    @ColumnInfo(name = "production_countries")
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryModel>?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: String,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate: String?,

    @ColumnInfo(name = "runtime")
    @SerializedName("runtime")
    val runtime: String?,

    @ColumnInfo(name = "spoken_languages")
    @SerializedName("spoken_languages")
    val spokenLanguages: List<LanguageModel>?,

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    val voteCount: String,

    @ColumnInfo(name = "movieType") val movieType: Int,
    @ColumnInfo(name = "fav") var favorite: Int = 0,

    ) : Parcelable

