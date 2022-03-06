package com.example.codigocodemanagement.service

import com.example.codigocodemanagement.model.CastResponseModel
import com.example.codigocodemanagement.model.MovieResponseModel
import com.example.codigocodemanagement.room.entity.MovieEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIService {
    @GET("/3/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieResponseModel>

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieResponseModel>

    @GET("/3/movie/{id}/credits")
    suspend fun getCasts(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<CastResponseModel>

    @GET("/3/movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieEntity>
}
