package com.ahmedjamion.todolist.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahmedjamion.todolist.domain.model.ToDo

@Composable
fun ToDoList(
    toDos: List<ToDo>,
    onToggleDone: (ToDo) -> Unit,
    onDelete: (ToDo) -> Unit,
    onEdit: (ToDo) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (toDos.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(bottom = 80.dp),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(
                    items = toDos,
                    key = { it.id }
                ) { toDo ->
                    ToDoItem(
                        toDo = toDo,
                        onToggleDone = { onToggleDone(toDo) },
                        onDelete = {
                            onDelete(toDo)
                        },
                        onEdit = { onEdit(toDo) },
                        modifier = Modifier.animateItem()
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No ToDos Found")
            }
        }
    }
}