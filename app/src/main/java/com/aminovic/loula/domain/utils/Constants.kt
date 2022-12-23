package com.aminovic.loula.domain.utils

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

internal object Constants {
    internal object Tables {
        internal const val SONGS = "songs"
        internal const val ARTISTS = "artists"
        internal const val ALBUMS = "albums"
        internal const val PLAYING_QUEUE = "playing_queue"
    }

    internal object Fields {
        internal const val ID = "id"
        internal const val MEDIA_ID = "media_id"
        internal const val ARTIST_ID = "artist_id"
        internal const val ALBUM_ID = "album_id"
        internal const val MEDIA_URI = "media_uri"
        internal const val ARTWORK_URI = "artwork_uri"
        internal const val TITLE = "title"
        internal const val ARTIST = "artist"
        internal const val ALBUM = "album"
        internal const val NAME = "name"
        internal const val NUMBER_OF_SONGS = "number_of_songs"
    }

    private const val PLAYING_QUEUE_INDEX_NAME = "playing_queue_index"
    val PLAYING_QUEUE_INDEX = intPreferencesKey(PLAYING_QUEUE_INDEX_NAME)

    private const val PREFERENCES_NAME = "preferences"
    val Context.preferencesDataStore by preferencesDataStore(name = PREFERENCES_NAME)
}
