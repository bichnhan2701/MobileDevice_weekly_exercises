package com.example.theme.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("theme_prefs")

class DataStoreManager(private val context: Context) {
    private val THEME_COLOR_KEY = stringPreferencesKey("theme_color")

    val themeColorFlow: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[THEME_COLOR_KEY] }

    suspend fun saveThemeColor(colorHex: String) {
        context.dataStore.edit { preferences ->
            preferences[THEME_COLOR_KEY] = colorHex
        }
    }
}
