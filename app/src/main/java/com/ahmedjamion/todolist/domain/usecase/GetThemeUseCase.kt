package com.ahmedjamion.todolist.domain.usecase

import com.ahmedjamion.todolist.domain.repository.ThemeRepository

class GetThemeUseCase(
    private val repository: ThemeRepository
) {
    operator fun invoke() = repository.getTheme()
}