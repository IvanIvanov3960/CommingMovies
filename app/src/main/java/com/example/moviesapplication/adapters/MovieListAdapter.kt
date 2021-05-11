package com.example.moviesapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapplication.databinding.SingleItemBinding
import com.example.moviesapplication.model.SingleMovie

class MovieListAdapter(val onClickListener: OnClickListener) : ListAdapter<SingleMovie, MovieListAdapter.MovieViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<SingleMovie>() {
        override fun areItemsTheSame(oldItem: SingleMovie, newItem: SingleMovie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SingleMovie, newItem: SingleMovie): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (singleMovie: SingleMovie) -> Unit) {
        fun onClick(singleMovie: SingleMovie) = clickListener(singleMovie)
    }

    class MovieViewHolder(private var binding: SingleItemBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(singleMovie: SingleMovie) {
            binding.movie = singleMovie
            binding.movieRating.rating = getRating(singleMovie)
            binding.executePendingBindings()
        }

        private fun getRating(singleMovie: SingleMovie): Float {
            return (singleMovie.ratings.sum() / singleMovie.ratings.size).toFloat()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.MovieViewHolder {
        return MovieViewHolder(SingleItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MovieListAdapter.MovieViewHolder, position: Int) {
        val singleMovie = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(singleMovie)
        }
        holder.bind(singleMovie)
    }
}