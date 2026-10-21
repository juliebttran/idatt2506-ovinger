package com.example.oving7


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "person",
    indices = [Index(value = ["name"], unique = true)]
)

data class Person(
    @PrimaryKey(autoGenerate = true)
    val personId: Long = 0,
    val name: String
)
