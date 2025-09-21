package com.ahmedjamion.todolist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedjamion.todolist.domain.model.ToDo
import com.ahmedjamion.todolist.domain.usecase.AddToDoUseCase
import com.ahmedjamion.todolist.domain.usecase.DeleteToDoUseCase
import com.ahmedjamion.todolist.domain.usecase.GetToDosUseCase
import com.ahmedjamion.todolist.domain.usecase.ToggleToDoUseCase
import com.ahmedjamion.todolist.domain.usecase.UpdateToDoUseCase
import com.ahmedjamion.todolist.presentation.state.ToDoFilter
import com.ahmedjamion.todolist.presentation.state.ToDoListScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val addToDoUseCase: AddToDoUseCase,
    private val deleteToDoUseCase: DeleteToDoUseCase,
    private val toggleToDoUseCase: ToggleToDoUseCase,
    private val getToDosUseCase: GetToDosUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase,
) : ViewModel() {
//    private val _toDosState = MutableStateFlow<UiState<List<ToDo>>>(UiState.Loading)
//    val toDosState: StateFlow<UiState<List<ToDo>>> = _toDosState

    private val _toDosState = MutableStateFlow(ToDoListScreenUiState(isLoading = true))
    val toDosState: StateFlow<ToDoListScreenUiState> = _toDosState

    private val _events = MutableSharedFlow<ToDoEvent>()
    val events = _events.asSharedFlow()

    init {
        viewModelScope.launch {
            try {
                getToDosUseCase()
                    .collect { toDos ->
                        _toDosState.update {
                            it.copy(
                                isLoading = false,
                                error = null,
                                toDos = toDos,
                                doneCount = toDos.count { toDo -> toDo.isDone },
                                pendingCount = toDos.count { toDo -> !toDo.isDone }
                            )
                        }
                    }
            } catch (e: Exception) {
                _toDosState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message,
                    )
                }
            }
        }
    }

    fun onFilterChanged(filter: ToDoFilter) {
        _toDosState.update {
            it.copy(
                filter = filter
            )
        }
    }

    fun addToDo(toDo: ToDo) {
        viewModelScope.launch {
            try {
                addToDoUseCase(toDo)
                _events.emit(ToDoEvent.ShowToast("New ToDo Added"))
            } catch (e: Exception) {
                _events.emit(ToDoEvent.ShowToast("Error Adding ToDo: ${e.message}"))
            }
        }
    }

    fun deleteToDo(toDo: ToDo) {
        viewModelScope.launch {
            try {
                deleteToDoUseCase(toDo)
                _events.emit(ToDoEvent.ShowToast("ToDo Deleted"))
            } catch (e: Exception) {
                _events.emit(ToDoEvent.ShowToast("Error Deleting ToDo: ${e.message}"))
            }
        }
    }

    fun toggleToDo(taskId: Int, isDone: Boolean) {
        viewModelScope.launch {
            try {
                toggleToDoUseCase(taskId, isDone)
                _events.emit(ToDoEvent.ShowToast("ToDo Toggled"))
            } catch (e: Exception) {
                _events.emit(ToDoEvent.ShowToast("Error Toggling ToDo: ${e.message}"))
            }
        }
    }

    fun updateToDo(toDo: ToDo) {
        viewModelScope.launch {
            try {
                updateToDoUseCase(toDo)
                _events.emit(ToDoEvent.ShowToast("ToDo Updated"))
            } catch (e: Exception) {
                _events.emit(ToDoEvent.ShowToast("Error Updating ToDo: ${e.message}"))
            }
        }
    }
}