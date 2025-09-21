package com.ahmedjamion.todolist.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertToDo(toDo: ToDoEntity)

    @Update
    suspend fun updateToDo(toDo: ToDoEntity)

    @Delete
    suspend fun deleteToDo(toDo: ToDoEntity)

    @Query("select * from todos order by id desc")
    fun getToDos(): Flow<List<ToDoEntity>>

    @Query("update todos set isDone = :isDone where id = :toDoId")
    suspend fun toggleToDo(toDoId: Int, isDone: Boolean)

    @Query("select * from todos where id = :toDoId")
    suspend fun getToDo(toDoId: Int): ToDoEntity
}