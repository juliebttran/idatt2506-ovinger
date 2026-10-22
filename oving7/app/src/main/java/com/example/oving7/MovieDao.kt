package com.example.oving7

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: Movie): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPerson(person: Person): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieActor(movieActor: MovieActor)

    @Query("SELECT personId FROM person WHERE name = :name")
    suspend fun personId(name: String): Long

    @Query("SELECT movie.title FROM movie")
    suspend fun findAllMovies(): List<String>

    @Query("""
        SELECT movie.title
        FROM movie
        INNER JOIN person
        ON movie.directorId = person.personId
        WHERE person.name = :director
    """)
    suspend fun findMovieByDirector(director: String): List<String>

    @Query("""
        SELECT person.name
        FROM person
        INNER JOIN movieactor
        ON person.personId = movieactor.personId
        INNER JOIN movie
        ON movieactor.movieId = movie.movieId
        WHERE movie.title = :title
    """)
    suspend fun findActorsInMovie(title: String): List<String>
}