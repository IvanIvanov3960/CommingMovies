package com.example.moviesapplication.movies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapplication.model.SingleMovie
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


val TAG = "database"

class WatchListViewModel(application: Application) : AndroidViewModel(application) {
    private val user: FirebaseAuth = FirebaseAuth.getInstance()
    private var database: DatabaseReference = Firebase.database.reference

   val _moviesToWatch = MutableLiveData<List<SingleMovie>>()

    val moviesToWatch: LiveData<List<SingleMovie>>
        get() = _moviesToWatch

    fun getWatchList() {

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val post = dataSnapshot.child("watch list").child(user.uid.toString()).children
                post.forEach {
                    val s = it.value
                    val td: Map<String, Any>? = s as HashMap<String, Any>?
                    val tt: MyMovies = MyMovies(
                            s?.get("releaseDate").toString(),
                            s?.get("year").toString(),
                            s?.get("imdbRating").toString(),
                            s?.get("title").toString().toList(),
                            listOf(s?.get("duration")) as List<Long>,
                            s?.get("actors").toString(),
                            s?.get("posterurl").toString(),
                            s?.get("genres").toString(),
                            s?.get("originalTitle").toString(),
                            s?.get("ratings") as Long,
                            s?.get("storyline").toString(),
                            s?.get("contentRating").toString(),
                            s?.get("id").toString().toList(),
                            s?.get("poster").toString())
                    val a = 3
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(postListener)
        
    }
}

data class MyMovies(
    val id: String,
    val title: String,
    val year: String,
    val genres: List<Char>,
    val ratings: List<Long>,
    val poster: String,
    val contentRating: String,
    val duration: String,
    val releaseDate: String,
    val averageRating: Long,
    val originalTitle: String,
    val storyline: String,
    val actors: List<Char>,
    val imdbRating: String,
    val posterurl: String) {
    constructor(
        id: String,
        title: String,
        year: String,
        genres: List<Char>,
        ratings: List<Long>,
        poster: String,
        contentRating: String,
        duration: String,
        releaseDate: String,
        averageRating: Long,
        originalTitle: String,
        storyline: String,
        actors: List<Char>,
        imdbRating: String
    ) : this()
} 
