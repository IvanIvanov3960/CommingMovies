package com.example.moviesapplication.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.model.SingleMovie
import com.example.moviesapplication.network.ApplicationApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListViewModel : ViewModel() {

    val _movies = MutableLiveData<List<SingleMovie>>()

    val movies: LiveData<List<SingleMovie>>
        get() = _movies

    private val _navigateToSelectedProperty = MutableLiveData<SingleMovie>()

    val navigateToSelectedProperty: LiveData<SingleMovie>
        get() = _navigateToSelectedProperty

    init {
        getMoviesList()
    }

    fun getMoviesList() {
        viewModelScope.launch {
            ApplicationApi.retrofitService.getMovies().enqueue(object:
                Callback<List<SingleMovie>> {
                override fun onResponse(call: Call<List<SingleMovie>>, response: Response<List<SingleMovie>>) {
                    _movies.value = response.body()
                }

                override fun onFailure(call: Call<List<SingleMovie>>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        }
    }

    fun displayMovieDetails(singleMovie: SingleMovie) {
        _navigateToSelectedProperty.value = singleMovie
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}