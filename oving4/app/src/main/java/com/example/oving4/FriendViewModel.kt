package com.example.oving4

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FriendViewModel : ViewModel() {
    private val _friends = MutableStateFlow(listOf<Friend>())
    val friends: StateFlow<List<Friend>> = _friends.asStateFlow()

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()
    var _birthday = MutableStateFlow("")
    val birthday: StateFlow<String> = _birthday.asStateFlow()

    fun changeName (value: String) {
       _name.value = value
    }

    fun changeBirthday (value: String) {
        _birthday.value = value
    }

    fun addFriend() {
        val nextId = (_friends.value.maxOfOrNull { it.id } ?: 0) + 1
        val friend = Friend(id = nextId, name = _name.value, birthday = _birthday.value)
        _friends.value = _friends.value + friend
        _name.value = ""
        _birthday.value = ""
    }

    fun updateFriendByIndex(index: Int) {
        val currentList = _friends.value.toMutableList()
        if (index in currentList.indices) {
            val oldFriend = currentList[index]
            currentList[index] = Friend(id = oldFriend.id, name = _name.value, birthday = _birthday.value)
            _friends.value = currentList
        }
        _name.value = ""
        _birthday.value = ""
    }

    fun editFriendByIndex(index: Int) {
        val friend = _friends.value.getOrNull(index)
        if (friend != null) {
            _name.value = friend.name
            _birthday.value = friend.birthday
        }
    }

}