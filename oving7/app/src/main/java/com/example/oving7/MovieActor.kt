package com.example.oving7

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = ["movieId", "personId"]
)

data class MovieActor(
    val movieId: Long,
    val personId: Long,
)
