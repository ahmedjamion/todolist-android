package com.ahmedjamion.todolist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedjamion.todolist.domain.usecase.GetThemeUseCase
import com.ahmedjamion.todolist.domain.usecase.SetThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    getThemeUseCase: GetThemeUseCase,
    private val setThemeUseCase: SetThemeUseCase
) : ViewModel() {
    val theme = getThemeUseCase()
        .stateIn(viewModelScope, SharingStarted.Eagerly, "system")

    fun setTheme(theme: String) {
        viewModelScope.launch {
            setThemeUseCase(theme)
        }
    }
}