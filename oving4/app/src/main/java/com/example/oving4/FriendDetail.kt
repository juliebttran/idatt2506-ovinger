package com.example.oving4

import kotlinx.serialization.Serializable

@Serializable
object FriendList

@Serializable
object AddFriend

@Serializable
data class EditFriend(val index: Int)

@Serializable
data class FriendDetail(val index: Int)
