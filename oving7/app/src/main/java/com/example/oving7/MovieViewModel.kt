package com.example.oving7

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val LOCAL_FILE = "movies_local.txt"

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val settings = SettingsStore(application)
    private val dao = Room.databaseBuilder(
        application, MovieDatabase::class.java, "library.db"
    ).build().movieDao()
    private val files = MovieFiles(application)
    private val _path = MutableStateFlow("")
    val path: StateFlow<String> = _path.asStateFlow()

    private val _result = MutableStateFlow<List<String>>(emptyList())
    val result: StateFlow<List<String>> = _result.asStateFlow()

    private val _color = MutableStateFlow(SettingsStore.DEFAULT_COLOR)
    val color: StateFlow<String> = _color.asStateFlow()

    init {
        viewModelScope.launch {
            _color.value = settings.backgroundColor()
            try {
                val lines = files.readPacked(R.raw.movies)

                files.writeLocal(LOCAL_FILE, lines)
                _result.value = files.readLocal(LOCAL_FILE)
                _path.value = files.localPath(LOCAL_FILE)

                for (line in lines) {
                    if (line.isNotBlank()) {
                        val parts = line.split(";")
                        if (parts.size >= 2) {
                            val movie = parts[0]
                            val director = parts[1]
                            val actors = parts[2]
                            dao.insertPerson(Person(name = director))
                            val directorId = dao.personId(director)
                            val movieId = dao.insertMovie(Movie(title = movie, directorId = directorId))

                            val actorNames = actors.split(",")
                            for (actorName in actorNames) {
                                if (actorName.isNotBlank()) {
                                    dao.insertPerson(Person(name = actorName))
                                    val actorId = dao.personId(actorName)
                                    dao.insertMovieActor(MovieActor(movieId = movieId, personId = actorId))
                                }
                            }
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

    fun findAllMovies() {
        viewModelScope.launch {
            _result.value = dao.findAllMovies()
        }
    }

    fun findMovieByDirector(director: String) {
        viewModelScope.launch {
            _result.value = dao.findMovieByDirector(director)
        }
    }

    fun findActorsInMovie(title: String) {
        viewModelScope.launch {
            _result.value = dao.findActorsInMovie(title)
        }
    }

    fun chooseColor(value: String) {
        viewModelScope.launch {
            settings.setBackgroundColor(value)
            _color.value = value
        }
    }
}