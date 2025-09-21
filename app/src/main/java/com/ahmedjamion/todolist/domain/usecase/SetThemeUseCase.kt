package com.ahmedjamion.todolist.domain.usecase

import com.ahmedjamion.todolist.domain.repository.ThemeRepository

class SetThemeUseCase(
    private val repository: ThemeRepository
) {
    suspend operator fun invoke(theme: String) = repository.setTheme(theme)
}