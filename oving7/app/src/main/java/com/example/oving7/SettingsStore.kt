package com.example.oving7

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.settings: DataStore<Preferences> by
    preferencesDataStore(name = "settings")

private val BACKGROUND_COLOR = stringPreferencesKey("background_color")
class SettingsStore(private val context: Context) {
    suspend fun backgroundColor(): String =
        context.settings.data.first()[BACKGROUND_COLOR] ?: DEFAULT_COLOR
    suspend fun setBackgroundColor(value: String) {
        context.settings.edit { preferences ->
            preferences[BACKGROUND_COLOR] = value
        }
    }
    companion object {
        const val DEFAULT_COLOR: String = "ingen"
    }
}