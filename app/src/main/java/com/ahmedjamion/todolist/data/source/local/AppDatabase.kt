package com.ahmedjamion.todolist.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ToDoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): ToDoDao
}