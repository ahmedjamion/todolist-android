package com.ahmedjamion.todolist.domain.usecase

import com.ahmedjamion.todolist.domain.model.ToDo
import com.ahmedjamion.todolist.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow

class GetToDosUseCase(
    private val toDoRepository: ToDoRepository
) {
    operator fun invoke(): Flow<List<ToDo>> =
        toDoRepository.getToDos()
}