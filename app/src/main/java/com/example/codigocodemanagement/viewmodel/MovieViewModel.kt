package com.example.codigocodemanagement.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codigocodemanagement.model.CastModel
import com.example.codigocodemanagement.repository.MovieRepository
import com.example.codigocodemanagement.room.entity.MovieEntity
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val popularMovieListLiveData = MutableLiveData<ArrayList<MovieEntity>>()
    val upcomingMovieListLiveData = MutableLiveData<ArrayList<MovieEntity>>()
    val castListLiveData = MutableLiveData<ArrayList<CastModel>>()
    val movieDetailLiveData = MutableLiveData<MovieEntity?>()
    val localMovieListPopularLiveData = MutableLiveData<List<MovieEntity>>()
    val localMovieListUpcomingLiveData = MutableLiveData<List<MovieEntity>>()


    fun getPopularMovies() = viewModelScope.launch {
        popularMovieListLiveData.postValue(movieRepository.getPopularMovies())
    }

    fun getUpcomingMovies() = viewModelScope.launch {
        upcomingMovieListLiveData.postValue(movieRepository.getUpcomingMovies())
    }

    fun getCasts(movieId: Int) = viewModelScope.launch {
        castListLiveData.postValue(movieRepository.getCasts(movieId))
    }

    fun getDetail(movieId: Int) = viewModelScope.launch {
        movieDetailLiveData.postValue(movieRepository.getMovieDetail(movieId))
    }

    fun getMoviesFromDB(status: Int) =
        viewModelScope.launch {
            when (status) {
                0 -> localMovieListPopularLiveData.postValue(movieRepository.getMoviesFromDB(status))
                1 -> localMovieListUpcomingLiveData.postValue(movieRepository.getMoviesFromDB(status))
            }
        }

    fun updateFav(status: Int, id: Int) = viewModelScope.launch {
        movieRepository.updateFav(status, id)
    }

    suspend fun getFav(id: Int): Int? {
        return movieRepository.getFav(id)
    }
}