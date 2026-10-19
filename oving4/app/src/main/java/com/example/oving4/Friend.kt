package com.example.oving4

data class Friend (
    val id: Int,
    val name: String,
    val birthday: String,
)

val sampleFriends: List<Friend> = listOf(
    Friend(0,"Julie", "22.08.02"),
    Friend(1,"Espen", "24.12.98"),
)