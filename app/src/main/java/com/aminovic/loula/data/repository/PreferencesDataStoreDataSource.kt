package com.aminovic.loula.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.aminovic.loula.domain.utils.Constants.PLAYING_QUEUE_INDEX
import kotlinx.coroutines.flow.map

class PreferencesDataStoreDataSource(
    private val dataStore: DataStore<Preferences>
) {
    fun getPlayingQueueIndex() = dataStore.data.map { preferences ->
        preferences[PLAYING_QUEUE_INDEX] ?: 0
    }

    suspend fun setPlayingQueueIndex(index: Int) {
        dataStore.edit { preferences ->
            preferences[PLAYING_QUEUE_INDEX] = index
        }
    }
}
