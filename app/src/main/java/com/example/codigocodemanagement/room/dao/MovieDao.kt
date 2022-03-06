package com.example.codigocodemanagement.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.codigocodemanagement.room.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: MovieEntity)

    @Query("SELECT * FROM tbl_movie WHERE movieType=:status")
    suspend fun getAllMovies(status: Int): List<MovieEntity>

    @Query("UPDATE tbl_movie SET fav=:status WHERE id =:id")
    suspend fun updateFav(status: Int,id:Int)

    @Query("SELECT fav FROM tbl_movie WHERE id=:id")
    suspend fun getFavStatus(id: Int): Int?

    @Query("DELETE FROM tbl_movie WHERE movieType=:status")
    suspend fun deleteAllMovies(status: Int)

}