package com.ahmedjamion.todolist.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahmedjamion.todolist.presentation.screen.SettingsScreen
import com.ahmedjamion.todolist.presentation.screen.ToDoListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListNavHost() {
    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    val canPop = navController.previousBackStackEntry != null

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Crossfade(
                        targetState = currentDestination?.route
                    ) { route ->
                        Text(
                            text = when (route) {
                                "settings" -> "Settings"
                                else -> "To Do List"
                            }
                        )
                    }
                },
                actions = {
                    Crossfade(targetState = canPop) { canPop ->
                        if (canPop) {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = "Options"
                                )
                            }
                        } else {
                            IconButton(onClick = {
                                navController.navigate("settings")
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Settings,
                                    contentDescription = "Options"
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )

        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding),
        ) {
            composable("home") { ToDoListScreen() }
            composable("settings") { SettingsScreen() }
        }
    }
}
