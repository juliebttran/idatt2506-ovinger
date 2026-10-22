package com.example.oving7

import androidx.room.Entity

@Entity(
    primaryKeys = ["movieId", "personId"]
)

data class MovieActor(
    val movieId: Long,
    val personId: Long,
)
