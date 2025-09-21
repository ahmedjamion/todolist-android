package com.ahmedjamion.todolist.domain.repository

import com.ahmedjamion.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    suspend fun addToDo(toDo: ToDo)
    suspend fun deleteToDo(toDo: ToDo)
    suspend fun toggleToDo(toDoId: Int, isDone: Boolean)
    fun getToDos(): Flow<List<ToDo>>
    suspend fun updateToDo(toDo: ToDo)
}