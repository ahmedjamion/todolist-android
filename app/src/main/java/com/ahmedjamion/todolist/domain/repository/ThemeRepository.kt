package com.ahmedjamion.todolist.domain.repository

import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    fun getTheme(): Flow<String>
    suspend fun setTheme(theme: String)
}