package com.ahmedjamion.todolist.di

import com.ahmedjamion.todolist.data.repository.ToDoRepositoryImpl
import com.ahmedjamion.todolist.data.source.local.ToDoDao
import com.ahmedjamion.todolist.domain.repository.ToDoRepository
import com.ahmedjamion.todolist.domain.usecase.AddToDoUseCase
import com.ahmedjamion.todolist.domain.usecase.DeleteToDoUseCase
import com.ahmedjamion.todolist.domain.usecase.GetToDosUseCase
import com.ahmedjamion.todolist.domain.usecase.ToggleToDoUseCase
import com.ahmedjamion.todolist.domain.usecase.UpdateToDoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideToDoRepository(dao: ToDoDao): ToDoRepository = ToDoRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideAddToDoUseCase(repo: ToDoRepository) = AddToDoUseCase(repo)

    @Provides
    @Singleton
    fun provideDeleteToDoUseCase(repo: ToDoRepository) = DeleteToDoUseCase(repo)

    @Provides
    @Singleton
    fun provideToggleToDoUseCase(repo: ToDoRepository) = ToggleToDoUseCase(repo)

    @Provides
    @Singleton
    fun provideGetToDosUseCase(repo: ToDoRepository) = GetToDosUseCase(repo)


    @Provides
    @Singleton
    fun provideUpdateToDoUseCase(repo: ToDoRepository) = UpdateToDoUseCase(repo)
}