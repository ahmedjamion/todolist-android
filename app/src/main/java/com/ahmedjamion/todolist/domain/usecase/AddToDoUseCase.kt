package com.ahmedjamion.todolist.domain.usecase

import com.ahmedjamion.todolist.domain.model.ToDo
import com.ahmedjamion.todolist.domain.repository.ToDoRepository

class AddToDoUseCase(
    private val toDoRepository: ToDoRepository
) {

    suspend operator fun invoke(toDo: ToDo) {

        if (!toDo.isValidTitle()) {
            throw IllegalArgumentException("The title of the to-do can't be empty.")
        }
        toDoRepository.addToDo(toDo)
    }

}