package com.ahmedjamion.todolist.domain.usecase

import com.ahmedjamion.todolist.domain.model.ToDo
import com.ahmedjamion.todolist.domain.repository.ToDoRepository

class AddToDoUseCase(
    private val toDoRepository: ToDoRepository
) {

    suspend operator fun invoke(toDo: ToDo) =
        toDoRepository.addToDo(toDo)
}