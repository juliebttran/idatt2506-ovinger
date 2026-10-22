package com.example.oving7

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(
    tableName = "movie",
    indices = [Index(value = ["title"], unique = true)]
)

data class Movie(
    @PrimaryKey(autoGenerate = true)
    val movieId: Long = 0,
    val title: String,
    val directorId: Long
)
