package com.example.moviesapplication.movies

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviesapplication.R
import com.example.moviesapplication.databinding.WatchListFragmentBinding

class WatchListFragment : Fragment() {

    private val viewModel: WatchListViewModel by lazy {
        ViewModelProvider(this).get(WatchListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = WatchListFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        viewModel.getWatchList()

        return binding.root
    }

}