package com.ahmedjamion.todolist.data.repository

import com.ahmedjamion.todolist.data.mapper.toDomain
import com.ahmedjamion.todolist.data.mapper.toEntity
import com.ahmedjamion.todolist.data.source.local.ToDoDao
import com.ahmedjamion.todolist.domain.model.ToDo
import com.ahmedjamion.todolist.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ToDoRepositoryImpl(
    private val dao: ToDoDao
) : ToDoRepository {
    override suspend fun addToDo(toDo: ToDo) {
        dao.insertToDo(toDo.toEntity())
    }

    override suspend fun deleteToDo(toDo: ToDo) {
        dao.deleteToDo(toDo.toEntity())
    }

//    override suspend fun toggleToDo(toDoId: Int, isDone: Boolean) {
//        dao.toggleToDo(toDoId, isDone)
//    }

    override fun getToDos(): Flow<List<ToDo>> =
        dao.getToDos().map { entities ->
            entities.map { entity -> entity.toDomain() }
        }


    override suspend fun updateToDo(toDo: ToDo) {
        dao.updateToDo(toDo.toEntity())
    }
}