package com.example.videoteca.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.videoteca.databinding.ItemMovieBinding
import com.example.videoteca.model.Movie
import com.example.videoteca.utils.loadImage

class MoviesViewHolder (private val itemBinding: ItemMovieBinding): RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(movie: Movie){
        itemBinding.movieTitle.text = movie.name
        movie.cover?.let {cover ->
            itemBinding.movieCover.loadImage(cover)
        }
    }
}