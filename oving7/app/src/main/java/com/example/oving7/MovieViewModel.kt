package com.example.oving7

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val database = Room.databaseBuilder(
        application, MovieDatabase::class.java, "movie.db"
    ).build()

    private val dao = database.movieDao()

    private val files = MovieFiles(application)

    private val _result = MutableStateFlow<List<String>>(emptyList())
    val result: StateFlow<List<String>> = _result.asStateFlow()


    init {
        viewModelScope.launch {
            try {
                val lines = files.readPacked(R.raw.movies)
                for (line in lines) {
                    if (line.isNotBlank()) {
                        val parts = line.split(";")
                        if (parts.size >= 2) {
                            val movie = parts[0].trim()
                            val director = parts[1].trim()

                            dao.insertPerson(Person(name = director))
                            val directorId = dao.personId(director)
                            dao.insertMovie(Movie(title = movie, directorId = directorId))
                        }
                    }
                }
                _result.value = dao.findAllMovies()
            } catch (e: Exception) {
                e.printStackTrace()
                _result.value = listOf("Error: ${e.localizedMessage}")
            }
        }
    }
}