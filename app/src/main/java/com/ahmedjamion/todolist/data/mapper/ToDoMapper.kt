package com.ahmedjamion.todolist.data.mapper

import com.ahmedjamion.todolist.data.source.local.ToDoEntity
import com.ahmedjamion.todolist.domain.model.ToDo

fun ToDoEntity.toDomain(): ToDo = ToDo(
    id = id,
    title = title,
    isDone = isDone
)

fun ToDo.toEntity(): ToDoEntity = ToDoEntity(
    id = id,
    title = title,
    isDone = isDone,
)