package com.ahmedjamion.todolist.di

import android.content.Context
import androidx.room.Room
import com.ahmedjamion.todolist.data.source.local.AppDatabase
import com.ahmedjamion.todolist.data.source.local.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext
        appContext: Context
    ): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "todo_db"
        ).build()

    @Provides
    fun provideTaskDao(db: AppDatabase): ToDoDao = db.taskDao()
}