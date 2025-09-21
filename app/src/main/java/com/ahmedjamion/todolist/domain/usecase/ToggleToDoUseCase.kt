package com.ahmedjamion.todolist.domain.usecase

import com.ahmedjamion.todolist.domain.repository.ToDoRepository

class ToggleToDoUseCase(
    private val toDoRepository: ToDoRepository
) {
    suspend operator fun invoke(toDoId: Int, isDone: Boolean) =
        toDoRepository.toggleToDo(toDoId, isDone)
}