package com.ahmedjamion.todolist.presentation.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ahmedjamion.todolist.presentation.viewmodel.ThemeViewModel

@Composable
fun SettingsScreen(
    viewModel: ThemeViewModel = hiltViewModel()
) {
    val theme = viewModel.theme.collectAsState()
    val isDarkMode = when (theme.value) {
        "dark" -> true
        "light" -> false
        else -> isSystemInDarkTheme()
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Dark Mode",
                modifier = Modifier.weight(1f)
            )
            Switch(
                enabled = theme.value != "system",
                checked = isDarkMode,
                onCheckedChange = {
                    if (isDarkMode) {
                        viewModel.setTheme("light")
                    } else {
                        viewModel.setTheme("dark")
                    }
                }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Use System Theme",
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = theme.value == "system",
                onCheckedChange = {
                    if (theme.value != "system")
                        viewModel.setTheme("system")
                    else if (theme.value == "system") {
                        if (isDarkMode) viewModel.setTheme("dark")
                        if (!isDarkMode) viewModel.setTheme("light")
                    }
                }
            )
        }

    }
}