package com.hmibrahimsarkar.kabboloker_brakkhakbi.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.security.MessageDigest

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "kabyolokor_settings")

class ThemePreferencesRepository(private val context: Context) {

    private object PreferencesKeys {
        val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
        val APP_PASSWORD_HASH = stringPreferencesKey("app_password_hash")
        val SECURITY_QUESTION = stringPreferencesKey("security_question")
        val SECURITY_ANSWER_HASH = stringPreferencesKey("security_answer_hash")
        val EDITOR_TOP_BAR_NAME = stringPreferencesKey("editor_top_bar_name")
        val IS_NOTIFICATIONS_ENABLED = booleanPreferencesKey("is_notifications_enabled")
        val FONT_SIZE_PREFERENCE = stringPreferencesKey("font_size_preference")
    }

    val isDarkMode: Flow<Boolean?> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.IS_DARK_MODE]
    }

    val isNotificationsEnabled: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.IS_NOTIFICATIONS_ENABLED] ?: true
    }

    val fontSizePreference: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.FONT_SIZE_PREFERENCE] ?: "medium"
    }

    val editorTopBarName: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.EDITOR_TOP_BAR_NAME] ?: "কাব্যলোকের ব্রক্ষকবি"
    }

    val appPasswordHash: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.APP_PASSWORD_HASH]
    }

    val securityQuestion: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.SECURITY_QUESTION]
    }

    suspend fun setDarkMode(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_DARK_MODE] = isDark
        }
    }

    suspend fun setNotificationsEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_NOTIFICATIONS_ENABLED] = enabled
        }
    }

    suspend fun setFontSizePreference(size: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.FONT_SIZE_PREFERENCE] = size
        }
    }

    suspend fun setEditorTopBarName(name: String) {
        context.dataStore.edit { preferences ->
            if (name.isBlank() || name == "কাব্যলোকের ব্রক্ষকবি") {
                preferences.remove(PreferencesKeys.EDITOR_TOP_BAR_NAME)
            } else {
                preferences[PreferencesKeys.EDITOR_TOP_BAR_NAME] = name.trim()
            }
        }
    }

    suspend fun resetEditorTopBarName() {
        context.dataStore.edit { preferences ->
            preferences.remove(PreferencesKeys.EDITOR_TOP_BAR_NAME)
        }
    }

    suspend fun setAppPassword(password: String, question: String = "", answer: String = "") {
        val hash = hashString(password)
        val answerHash = if (answer.isNotEmpty()) hashString(answer.lowercase().trim()) else ""
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.APP_PASSWORD_HASH] = hash
            if (question.isNotEmpty()) {
                preferences[PreferencesKeys.SECURITY_QUESTION] = question
            }
            if (answerHash.isNotEmpty()) {
                preferences[PreferencesKeys.SECURITY_ANSWER_HASH] = answerHash
            }
        }
    }

    suspend fun clearAppPassword() {
        context.dataStore.edit { preferences ->
            preferences.remove(PreferencesKeys.APP_PASSWORD_HASH)
            preferences.remove(PreferencesKeys.SECURITY_QUESTION)
            preferences.remove(PreferencesKeys.SECURITY_ANSWER_HASH)
        }
    }

    fun verifyPassword(inputPassword: String, savedHash: String?): Boolean {
        if (savedHash == null || savedHash.isEmpty()) return true
        return hashString(inputPassword) == savedHash
    }

    private fun hashString(input: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
