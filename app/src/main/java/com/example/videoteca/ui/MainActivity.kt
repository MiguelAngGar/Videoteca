package com.example.videoteca.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import com.example.videoteca.adapter.MoviesAdapter
import com.example.videoteca.databinding.ActivityMainBinding
import com.example.videoteca.model.Movie
import com.example.videoteca.utils.getJsonFromAssets
import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val TAG = ":::TAG"

    private lateinit var adapter: MoviesAdapter
    private val copyList = arrayListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MoviesAdapter(::onMovieClicked)
        binding.recyclerView.adapter = adapter

        adapter.refreshList(getListFromJson())

        binding.searchField.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextChange(query: String?): Boolean {
                query?.let {
                    val filteredList = copyList.filter {
                        it.name?.lowercase(Locale.getDefault())!!.contains(query)
                    }
                    adapter.filterByName(filteredList)
                }
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                return false
            }
        })

        binding.btSort.setOnClickListener{
            adapter.orderByName()
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    private fun getListFromJson(): ArrayList<Movie> {
        var movieList = arrayListOf<Movie>()
        val json = getJsonFromAssets("movies.json")
        json?.let {
            movieList = ArrayList(Gson().fromJson(json, Array<Movie>::class.java).toList())
            copyList.addAll(movieList)
        }

        return ArrayList(movieList)
    }

    private fun onMovieClicked(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}