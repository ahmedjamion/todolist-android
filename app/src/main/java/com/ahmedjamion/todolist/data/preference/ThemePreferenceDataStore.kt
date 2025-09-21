package com.ahmedjamion.todolist.data.preference

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("settings")

class ThemePreferenceDataStore(private val context: Context) {
    private val themeMode = stringPreferencesKey("theme_mode")

    fun getTheme() = context.dataStore.data.map { prefs ->
        prefs[themeMode] ?: "system"
    }

    suspend fun setTheme(theme: String) {
        context.dataStore.edit { prefs ->
            prefs[themeMode] = theme
        }
    }
}