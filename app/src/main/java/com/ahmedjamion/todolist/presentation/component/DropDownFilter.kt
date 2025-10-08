package com.ahmedjamion.todolist.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahmedjamion.todolist.presentation.state.ToDoFilter

@Composable
fun DropDownFilter(
    filter: String,
    onFilter: (String) -> Unit
) {
    Box {
        val filters = ToDoFilter.entries
        var expanded by remember { mutableStateOf(false) }
        TextButton(onClick = { expanded = true }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(filter.toString())
                Spacer(Modifier.width(4.dp))
                Icon(imageVector = Icons.Filled.Tune, contentDescription = "Delete")
            }
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            filters.forEach { filter ->
                DropdownMenuItem(
                    text = { Text(filter.toString()) },
                    onClick = {
                        onFilter(filter.name)
                        expanded = false
                    }
                )
            }
        }
    }
}