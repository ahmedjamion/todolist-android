package com.ahmedjamion.todolist.di

import android.content.Context
import com.ahmedjamion.todolist.data.preference.ThemePreferenceDataStore
import com.ahmedjamion.todolist.data.repository.ThemeRepositoryImpl
import com.ahmedjamion.todolist.domain.repository.ThemeRepository
import com.ahmedjamion.todolist.domain.usecase.GetThemeUseCase
import com.ahmedjamion.todolist.domain.usecase.SetThemeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ThemeModule {
    @Provides
    @Singleton
    fun provideThemePreferenceDataStore(
        @ApplicationContext context: Context
    ): ThemePreferenceDataStore = ThemePreferenceDataStore(context)

    @Provides
    @Singleton
    fun provideThemeRepository(
        prefs: ThemePreferenceDataStore
    ): ThemeRepository = ThemeRepositoryImpl(prefs)

    @Provides
    @Singleton
    fun provideGetThemeUseCase(repo: ThemeRepository) = GetThemeUseCase(repo)

    @Provides
    @Singleton
    fun provideSetThemeUseCase(repo: ThemeRepository) = SetThemeUseCase(repo)
}