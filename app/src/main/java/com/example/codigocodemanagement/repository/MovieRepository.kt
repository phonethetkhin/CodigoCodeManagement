package com.example.codigocodemanagement.repository

import android.content.Context
import android.util.Log
import com.example.codigocodemanagement.common.API_KEY
import com.example.codigocodemanagement.model.CastModel
import com.example.codigocodemanagement.room.MovieDB
import com.example.codigocodemanagement.room.entity.MovieEntity
import com.example.codigocodemanagement.service.APIService
import com.example.codigocodemanagement.utility.showToast
import okio.IOException

class MovieRepository(
    private val context: Context,
    val apiService: APIService,
    val movieDB: MovieDB
) {

    suspend fun getPopularMovies(): ArrayList<MovieEntity> {
        val popularMovieList = ArrayList<MovieEntity>()
        try {
            val response = apiService.getPopularMovies(API_KEY, 1)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    if (movieDB.movieDao().getAllMovies(1).count() == 0) {
                        response.body()!!.results.forEach { current ->
                            val mEn = MovieEntity(
                                current.id,
                                current.title,
                                current.overview,
                                current.posterPath,
                                current.backdropPath,
                                current.genres,
                                current.productionCountries,
                                current.voteAverage,
                                current.releaseDate,
                                current.runtime,
                                current.spokenLanguages,
                                current.voteCount,
                                0, 0
                            )
                            movieDB.movieDao().insertMovie(mEn)
                        }
                    }
                    popularMovieList.addAll(response.body()!!.results)
                }
            } else {
                context.showToast("Some error occurred when fetching popular movies.")
            }
        } catch (e: Exception) {
            if (e is IOException)
                context.showToast("No internet connection, please check your mobile data or wifi")
            else
                context.showToast("Some Error Occurred, ${e.localizedMessage}")

        }
        return popularMovieList
    }

    suspend fun getUpcomingMovies(): ArrayList<MovieEntity> {
        val movieModelList = ArrayList<MovieEntity>()
        try {
            val response = apiService.getUpcomingMovies(API_KEY, 1)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    if (movieDB.movieDao().getAllMovies(1).count() == 0) {
                        response.body()!!.results.forEach { current ->
                            val mEn = MovieEntity(
                                current.id,
                                current.title,
                                current.overview,
                                current.posterPath,
                                current.backdropPath,
                                current.genres,
                                current.productionCountries,
                                current.voteAverage,
                                current.releaseDate,
                                current.runtime,
                                current.spokenLanguages,
                                current.voteCount,
                                1, 0
                            )
                            movieDB.movieDao().insertMovie(mEn)
                        }
                    }

                    movieModelList.addAll(response.body()!!.results)
                }
            } else {
                context.showToast("Some error occurred when fetching upcoming movies.")
            }
        } catch (e: Exception) {
            if (e is IOException)
                context.showToast("No internet connection, please check your mobile data or wifi")
            else
                context.showToast("Some Error Occurred, ${e.localizedMessage}")
        }

        return movieModelList
    }

    suspend fun getCasts(movieId: Int): ArrayList<CastModel> {
        val castList = ArrayList<CastModel>()
        try {
            val response = apiService.getCasts(movieId, API_KEY)
            if (response.isSuccessful) {
                if (response.body() != null)
                    castList.addAll(response.body()!!.cast)
            } else {
                context.showToast("Some error occurred when fetching casts.")
            }
        } catch (e: Exception) {
            if (e is IOException)
                context.showToast("No internet connection, please check your mobile data or wifi")
            else
                context.showToast("Some Error Occurred ${e.localizedMessage}")
        }
        return castList
    }

    suspend fun getMovieDetail(movieId: Int): MovieEntity? {
        var movieDetail: MovieEntity? = null
        try {
            val response = apiService.getMovieDetail(movieId, API_KEY)
            if (response.isSuccessful) {
                if (response.body() != null)
                    movieDetail = response.body()
            } else {
                context.showToast("Some error occurred when fetching casts.")
            }
        } catch (e: Exception) {
            if (e is IOException)
                context.showToast("No internet connection, please check your mobile data or wifi")
            else
                context.showToast("Some Error Occurred ${e.localizedMessage}")
        }

        return movieDetail
    }

    suspend fun getMoviesFromDB(status: Int): List<MovieEntity> =  movieDB.movieDao().getAllMovies(status)

    suspend fun updateFav(status: Int, id: Int) {
        movieDB.movieDao().updateFav(status, id)
    }

    suspend fun getFav(id: Int): Int? {
        return movieDB.movieDao().getFavStatus(id)
    }
}