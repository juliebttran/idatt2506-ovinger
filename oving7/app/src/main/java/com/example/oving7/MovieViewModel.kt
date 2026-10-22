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
            val lines = files.readPacked(R.raw.movies)
            for (line in lines) {
                val (movie, director, actor) = line.split(";")
                var actorId = dao.insertPerson(Person(name = actor))
                if (actorId == -1L) actorId = dao.peronId(actor)
            }
            _result.value = dao.allPeople()
        }
    }


}