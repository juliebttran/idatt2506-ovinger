package com.example.oving7

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Movie::class, Person::class, MovieActor::class],
    version = 1,
    exportSchema = false
)

abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
}