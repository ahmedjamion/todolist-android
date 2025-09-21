package com.ahmedjamion.todolist.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ahmedjamion.todolist.presentation.viewmodel.ThemeViewModel
import com.ahmedjamion.todolist.ui.theme.ToDoListTheme

@Composable
fun ToDoListApp() {
    val themeViewModel: ThemeViewModel = hiltViewModel()

    val theme = themeViewModel.theme.collectAsState()

    val darkTheme = when (theme.value) {
        "dark" -> true
        "light" -> false
        else -> isSystemInDarkTheme()
    }

    ToDoListTheme(darkTheme = darkTheme) {
        ToDoListNavHost()
    }
}