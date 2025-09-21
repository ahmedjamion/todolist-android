package com.ahmedjamion.todolist.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahmedjamion.todolist.domain.model.ToDo

@Composable
fun ToDoSheet(
    newTaskTitle: String,
    onTitleChange: (String) -> Unit,
    onSubmit: () -> Unit,
    toDo: ToDo? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = newTaskTitle,

            label = { Text("To Do Title") },
            placeholder = {
                Text("New To Do")
            },
            onValueChange = onTitleChange,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onSubmit,
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                if (toDo == null) {
                    "Add To Do"
                } else {
                    "Update To Do"
                }
            )
        }
    }
}