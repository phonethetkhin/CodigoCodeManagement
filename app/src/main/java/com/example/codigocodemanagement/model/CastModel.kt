package com.example.codigocodemanagement.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CastResponseModel(
    @SerializedName("cast")
    val cast: List<CastModel>,
) : Parcelable

@Parcelize
data class CastModel(
    @SerializedName("name")
    val name: String,

    @SerializedName("profile_path")
    val profilePath: String,
) : Parcelable