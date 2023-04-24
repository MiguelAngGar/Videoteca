package com.example.videoteca.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.videoteca.R
import com.example.videoteca.databinding.ActivityDetailBinding
import com.example.videoteca.databinding.ActivityMainBinding
import com.example.videoteca.model.Movie
import com.example.videoteca.utils.loadImage

class DetailActivity : AppCompatActivity() {

    private lateinit var bind: ActivityDetailBinding

    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)

        retrieveMovie()
        renderUI()
    }

    private fun retrieveMovie() {
        movie = intent.getSerializableExtra("movie") as Movie?
    }

    private fun renderUI() {

        val nameAndYear = movie?.name + " (" + movie?.year + ")"

        bind.detailName.text = nameAndYear
        bind.detailDescription.text = movie?.description
        movie?.cover?.let { mCover ->
            bind.detailImage.loadImage(mCover)
        }
    }
}