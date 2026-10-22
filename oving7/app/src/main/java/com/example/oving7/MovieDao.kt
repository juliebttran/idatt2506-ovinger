package com.example.oving7

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPerson(person: Person): Long

    @Query("SELECT personId FROM person WHERE name = :name")
    suspend fun peronId(name: String): Long

    @Query("SELECT name FROM person ORDER BY name")
    suspend fun allPeople(): List<String>
}