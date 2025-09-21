package com.ahmedjamion.todolist.ui.theme

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun SystemBar(
    darkTheme: Boolean,
    scrimColor: Int
) {
    val context = LocalContext.current
    SideEffect {
        val style =
            if (darkTheme) {
                SystemBarStyle.dark(scrimColor)
            } else {
                SystemBarStyle.light(
                    scrimColor,
                    darkScrim = scrimColor
                )
            }
        (context as? ComponentActivity)?.enableEdgeToEdge(
            statusBarStyle = style,
            navigationBarStyle = style
        )
    }
}