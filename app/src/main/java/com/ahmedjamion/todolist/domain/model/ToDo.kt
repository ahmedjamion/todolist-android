package com.ahmedjamion.todolist.domain.model

data class ToDo(
    val id: Int = 0,
    val title: String,
    val isDone: Boolean = false,
) {
    fun toggle(): ToDo {
        return this.copy(isDone = !isDone)
    }

    fun isValidTitle(): Boolean {
        return title.isNotBlank()
    }
}