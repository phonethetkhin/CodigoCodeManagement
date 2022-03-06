package com.example.codigocodemanagement.utility

import androidx.room.TypeConverter
import com.example.codigocodemanagement.model.GenreModel
import com.example.codigocodemanagement.model.LanguageModel
import com.example.codigocodemanagement.model.ProductionCountryModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object ObjectConverter {
    @TypeConverter
    fun fromGenreListToString(genres: List<GenreModel?>?): String? {
        if (genres == null) {
            return null
        }
        val gSon = Gson()
        val type: Type =
            object : TypeToken<List<GenreModel?>?>() {}.type
        return gSon.toJson(genres, type)
    }

    @TypeConverter
    fun fromStringToGenreList(genres: String?): List<GenreModel?>? {
        if (genres == null) {
            return null
        }
        val gSon = Gson()
        val type: Type =
            object : TypeToken<List<GenreModel?>?>() {}.type
        return gSon.fromJson(genres, type)
    }

    @TypeConverter
    fun fromProdCountriesToString(prodCountries: List<ProductionCountryModel?>?): String? {
        if (prodCountries == null) {
            return null
        }
        val gSon = Gson()
        val type: Type =
            object : TypeToken<List<ProductionCountryModel?>?>() {}.type
        return gSon.toJson(prodCountries, type)
    }

    @TypeConverter
    fun fromStringToProdCountries(prodCountries: String?): List<ProductionCountryModel?>? {
        if (prodCountries == null) {
            return null
        }
        val gSon = Gson()
        val type: Type =
            object : TypeToken<List<ProductionCountryModel?>?>() {}.type
        return gSon.fromJson(prodCountries, type)
    }

    @TypeConverter
    fun fromLanguageListToString(languages: List<LanguageModel?>?): String? {
        if (languages == null) {
            return null
        }
        val gSon = Gson()
        val type: Type =
            object : TypeToken<List<LanguageModel?>?>() {}.type
        return gSon.toJson(languages, type)
    }

    @TypeConverter
    fun fromStringToLanguageList(languages: String?): List<LanguageModel?>? {
        if (languages == null) {
            return null
        }
        val gSon = Gson()
        val type: Type =
            object : TypeToken<List<LanguageModel?>?>() {}.type
        return gSon.fromJson(languages, type)
    }
}