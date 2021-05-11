package com.example.moviesapplication.movie

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapplication.model.SingleMovie
import com.example.moviesapplication.movies.WatchListViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.Duration

class MovieViewModel(singleMovie: SingleMovie, application: Application) : AndroidViewModel(
    application
) {

    private var database: DatabaseReference = Firebase.database.reference
    private lateinit var hours: String
    private lateinit var minutes: String

    val user: FirebaseAuth = FirebaseAuth.getInstance()

    private val _selectedMovie = MutableLiveData<SingleMovie>()

    val selectedMovie: LiveData<SingleMovie>
        get() = _selectedMovie

    init {
        _selectedMovie.value = singleMovie
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDuration(): String {
            val dur = if (!_selectedMovie.value?.duration!!.isEmpty()) _selectedMovie.value?.duration else return ""
            val time: Duration = Duration.parse(dur)
            hours = time.toHours().toString()
            val allMinutes = time.toMinutes()
            if (allMinutes > 60) {
                minutes = (allMinutes - 60).toInt().toString()
            }
        return hours.plus(":").plus(minutes)
    }

    fun addToWatchList(singleMovie: SingleMovie) {
        database.child("watch list").child(user.uid.toString()).child(singleMovie.id).setValue(singleMovie)
    }
}