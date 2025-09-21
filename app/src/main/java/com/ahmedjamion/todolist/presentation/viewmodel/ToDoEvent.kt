package com.ahmedjamion.todolist.presentation.viewmodel

sealed class ToDoEvent {
    data class ShowToast(val message: String) : ToDoEvent()
}