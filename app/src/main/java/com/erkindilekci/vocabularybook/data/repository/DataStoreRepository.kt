package com.erkindilekci.vocabularybook.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.erkindilekci.vocabularybook.util.ColorFilter
import com.erkindilekci.vocabularybook.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.PREFERENCE_NAME)

@ViewModelScoped
class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private object PreferenceKey {
        val colorFilter = stringPreferencesKey(name = Constants.PREFERENCE_KEY)
    }

    private val dataStore = context.dataStore

    suspend fun persistColorFilter(colorFilter: ColorFilter) {
        dataStore.edit {
            it[PreferenceKey.colorFilter] = colorFilter.name
        }
    }

    val readColorFilter: Flow<String> = dataStore.data
        .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
        .map {
            val colorFilter = it[PreferenceKey.colorFilter] ?: ColorFilter.Purple.name
            colorFilter
        }
}
