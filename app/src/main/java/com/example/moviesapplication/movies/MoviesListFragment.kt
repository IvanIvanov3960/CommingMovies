package com.example.moviesapplication.movies

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.moviesapplication.MainActivity
import com.example.moviesapplication.R
import com.example.moviesapplication.adapters.MovieListAdapter
import com.example.moviesapplication.databinding.MoviesListFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MoviesListFragment : Fragment() {

    private val viewModel: MoviesListViewModel by lazy {
        ViewModelProvider(this).get(MoviesListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MoviesListFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.movies = viewModel

        binding.recyclerView.adapter = MovieListAdapter(MovieListAdapter.OnClickListener {
            viewModel.displayMovieDetails(it)
        })

        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if (null != it) {
                this.findNavController().navigate(MoviesListFragmentDirections.actionMoviesListFragmentToMovieFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        return binding.root
    }



//    @SuppressLint("ResourceType")
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            R.id.logout -> {
//                Firebase.auth.signOut()
//                val i = Intent(this@MainActivity, MainActivity::class.java)
//                startActivity(i)
//            }
//            R.id.watchList -> {
////                this.findNavController(R.navigation.navigation).navigate(R.layout.watch_list_fragment)
//                val i = Intent(this@MainActivity, WatchListFragment::class.java)
//                startActivity(i)
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

}