package com.ahmedjamion.todolist.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ahmedjamion.todolist.domain.model.ToDo
import com.ahmedjamion.todolist.presentation.component.DropDownFilter
import com.ahmedjamion.todolist.presentation.component.ToDoList
import com.ahmedjamion.todolist.presentation.component.ToDoSheet
import com.ahmedjamion.todolist.presentation.component.ToDosError
import com.ahmedjamion.todolist.presentation.component.ToDosLoading
import com.ahmedjamion.todolist.presentation.component.ToDosSummary
import com.ahmedjamion.todolist.presentation.state.ToDoFilter
import com.ahmedjamion.todolist.presentation.viewmodel.ToDoEvent
import com.ahmedjamion.todolist.presentation.viewmodel.ToDoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen(
    viewModel: ToDoViewModel = hiltViewModel(),
) {
    val toDosState by viewModel.toDosState.collectAsState()

    var newToDoTitle by remember { mutableStateOf("") }

    var showBottomSheet by remember { mutableStateOf(false) }

    var toDoToEdit by remember { mutableStateOf<ToDo?>(null) }

    var toDoToDelete by remember { mutableStateOf<ToDo?>(null) }

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .imePadding()
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Button(
            onClick = {
                showBottomSheet = true
            },
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(16.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .zIndex(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
                Text("New To Do")
            }
        }
        Spacer(Modifier.height(16.dp))

        when {
            toDosState.isLoading -> ToDosLoading()

            toDosState.error != null -> ToDosError(toDosState.error!!)

            else -> {

                val toDos = when (toDosState.filter) {
                    ToDoFilter.ALL -> toDosState.toDos
                    ToDoFilter.DONE -> toDosState.toDos.filter { it.isDone }
                    ToDoFilter.PENDING -> toDosState.toDos.filter { !it.isDone }
                }

                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ToDosSummary(
                            pendingCount = toDosState.pendingCount,
                            doneCount = toDosState.doneCount
                        )
                        DropDownFilter(
                            filter = toDosState.filter.toString(),
                            onFilter = { viewModel.onFilterChanged(ToDoFilter.valueOf(it)) }
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                    ToDoList(
                        toDos = toDos,
                        onToggleDone = { viewModel.toggleToDo(it.id, !it.isDone) },
                        onDelete = { toDo ->
                            toDoToDelete = toDo
                        },
                        onEdit = { toDo ->
                            toDoToEdit = toDo
                            showBottomSheet = true
                            newToDoTitle = toDo.title
                        },
                    )
                }
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    newToDoTitle = ""
                    toDoToEdit = null
                    showBottomSheet = false
                },
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                ),
            ) {
                ToDoSheet(
                    newTaskTitle = newToDoTitle,
                    onTitleChange = { newToDoTitle = it },
                    toDo = toDoToEdit,
                    onSubmit = {
                        if (newToDoTitle.isNotBlank()) {
                            if (toDoToEdit == null) {
                                viewModel.addToDo(ToDo(title = newToDoTitle))
                            } else {
                                viewModel.updateToDo(toDoToEdit!!.copy(title = newToDoTitle))
                            }
                            newToDoTitle = ""
                            toDoToEdit = null
                            showBottomSheet = false
                        } else {
                            Toast.makeText(
                                context, "You haven't typed anything.", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )

            }
        }

        if (toDoToDelete != null) {
            AlertDialog(
                onDismissRequest = { toDoToDelete = null },
                title = { Text("Delete To Do?") },
                text = { Text(toDoToDelete!!.title) },
                confirmButton = {
                    Button(onClick = {
                        viewModel.deleteToDo(toDoToDelete!!)
                        toDoToDelete = null
                    }) {
                        Text("Delete")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        toDoToDelete = null
                    }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }

    LaunchedEffect(
        Unit
    ) {
        viewModel.events.collect { event ->
            when (event) {
                is ToDoEvent.ShowToast -> Toast.makeText(
                    context, event.message, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}