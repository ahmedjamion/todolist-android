package com.ahmedjamion.todolist.data.repository

import com.ahmedjamion.todolist.data.preference.ThemePreferenceDataStore
import com.ahmedjamion.todolist.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow

class ThemeRepositoryImpl(
    private val prefs: ThemePreferenceDataStore
) : ThemeRepository {
    override fun getTheme(): Flow<String> = prefs.getTheme()

    override suspend fun setTheme(theme: String) = prefs.setTheme(theme)
}