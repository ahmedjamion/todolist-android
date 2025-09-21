package com.ahmedjamion.todolist.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmedjamion.todolist.domain.model.ToDo

@Composable
fun DeleteToDo(
    toDoToDelete: ToDo,
    deleteTask: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp
        )
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                "Delete this task?",
                fontSize = 24.sp
            )
            Text("Task: ${toDoToDelete.title}")
            Text("Status: ${if (toDoToDelete.isDone) "Done" else "Not Done"}")
        }
        Button(onClick = deleteTask) {
            Text("Delete")
        }
    }
}