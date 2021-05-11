package com.example.moviesapplication.movie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviesapplication.R
import com.example.moviesapplication.databinding.MovieFragmentBinding

class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this).get(MovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = MovieFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val singleMovie = MovieFragmentArgs.fromBundle(arguments!!).selectedMovie

        binding.favorite.setOnClickListener {
            binding.favorite.setImageResource(R.drawable.ic_baseline_star_24)
            viewModel.addToWatchList(singleMovie)
        }

        val viewModelFactory = DetailViewModelFactory(singleMovie, application)
        binding.model = ViewModelProvider(
            this, viewModelFactory).get(MovieViewModel::class.java)
        return binding.root
    }

    

}