package com.example.codigocodemanagement.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.codigocodemanagement.room.dao.MovieDao
import com.example.codigocodemanagement.room.entity.MovieEntity
import com.example.codigocodemanagement.utility.ObjectConverter

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ObjectConverter::class)

abstract class MovieDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDB? = null
        fun getInstance(context: Context): MovieDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDB::class.java, "CodigoMovie.db"
            ).build()
    }
}