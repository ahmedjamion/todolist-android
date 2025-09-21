package com.ahmedjamion.todolist.presentation.state

import com.ahmedjamion.todolist.domain.model.ToDo

enum class ToDoFilter {
    ALL,
    DONE,
    PENDING;

    override fun toString(): String =
        name.lowercase()
            .replaceFirstChar {
                it.titlecase()
            }
}

data class ToDoListScreenUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val toDos: List<ToDo> = emptyList<ToDo>(),
    val doneCount: Int = 0,
    val pendingCount: Int = 0,
    val filter: ToDoFilter = ToDoFilter.ALL
)
