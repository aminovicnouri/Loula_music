package com.aminovic.loula.exo_player

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.Player
import androidx.media3.common.Player.*
import androidx.media3.session.MediaBrowser
import androidx.media3.session.SessionToken
import com.aminovic.loula.di.Dispatcher
import com.aminovic.loula.di.LoulaDispatchers
import com.aminovic.loula.domain.data.Song
import com.aminovic.loula.domain.use_case.GetPlayingQueueIndexUseCase
import com.aminovic.loula.domain.use_case.GetPlayingQueueUseCase
import com.aminovic.loula.domain.use_case.SetPlayingQueueIndexUseCase
import com.aminovic.loula.domain.use_case.SetPlayingQueueUseCase
import com.aminovic.loula.domain.utils.MediaConstants.DEFAULT_INDEX
import com.aminovic.loula.domain.utils.MediaConstants.DEFAULT_POSITION_MS
import com.aminovic.loula.exo_player.common.MusicState
import com.aminovic.loula.exo_player.mapper.asMediaItem
import com.aminovic.loula.exo_player.mapper.asSong
import com.aminovic.loula.exo_player.mapper.asSongModel
import com.aminovic.loula.exo_player.util.Constants.POSITION_UPDATE_INTERVAL_MS
import com.aminovic.loula.exo_player.util.asPlaybackState
import com.aminovic.loula.exo_player.util.orDefaultTimestamp
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.guava.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicServiceConnection @Inject constructor(
    @ApplicationContext context: Context,
    @Dispatcher(LoulaDispatchers.MAIN) mainDispatcher: CoroutineDispatcher,
    private val getPlayingQueueUseCase: GetPlayingQueueUseCase,
    private val setPlayingQueueUseCase: SetPlayingQueueUseCase,
    private val getPlayingQueueIndexUseCase: GetPlayingQueueIndexUseCase,
    private val setPlayingQueueIndexUseCase: SetPlayingQueueIndexUseCase
) {
    private var mediaBrowser: MediaBrowser? = null
    private val coroutineScope = CoroutineScope(mainDispatcher + SupervisorJob())

    private val _musicState = MutableStateFlow(MusicState())
    val musicState = _musicState.asStateFlow()

    val currentPosition = flow {
        while (currentCoroutineContext().isActive) {
            val currentPosition = mediaBrowser?.currentPosition ?: DEFAULT_POSITION_MS
            emit(currentPosition)
            delay(POSITION_UPDATE_INTERVAL_MS)
        }
    }

    init {
        coroutineScope.launch {
            mediaBrowser = MediaBrowser.Builder(
                context,
                SessionToken(context, ComponentName(context, MusicService::class.java))
            ).buildAsync().await().apply { addListener(PlayerListener()) }
            updatePlayingQueue()
        }
    }

    fun skipPrevious() = mediaBrowser?.run {
        seekToPrevious()
        play()
    }

    fun play() = mediaBrowser?.play()
    fun pause() = mediaBrowser?.pause()

    fun skipNext() = mediaBrowser?.run {
        seekToNext()
        play()
    }

    fun skipTo(position: Long) = mediaBrowser?.run {
        seekTo(position)
        play()
    }

    fun playSongs(
        songs: List<Song>,
        startIndex: Int = DEFAULT_INDEX,
        startPositionMs: Long = DEFAULT_POSITION_MS
    ) {
        mediaBrowser?.run {
            setMediaItems(songs.map(Song::asMediaItem), startIndex, startPositionMs)
            prepare()
            play()
        }
        coroutineScope.launch {
            setPlayingQueueUseCase(songs.map(Song::asSongModel))
        }
    }

    fun shuffleSongs(
        songs: List<Song>,
        startIndex: Int = DEFAULT_INDEX,
        startPositionMs: Long = DEFAULT_POSITION_MS
    ) = playSongs(
        songs = songs.shuffled(),
        startIndex = startIndex,
        startPositionMs = startPositionMs
    )

    private inner class PlayerListener : Player.Listener {
        override fun onEvents(player: Player, events: Player.Events) {
            if (events.containsAny(
                    EVENT_PLAYBACK_STATE_CHANGED,
                    EVENT_MEDIA_METADATA_CHANGED,
                    EVENT_PLAY_WHEN_READY_CHANGED
                )
            ) {
                updateMusicState(player)
            }

            if (events.contains(EVENT_MEDIA_ITEM_TRANSITION)) {
                updatePlayingQueueIndex(player)
            }
        }
    }

    private fun updateMusicState(player: Player) = with(player) {
        _musicState.update {
            it.copy(
                currentSong = currentMediaItem.asSong(),
                playbackState = playbackState.asPlaybackState(),
                playWhenReady = playWhenReady,
                duration = duration.orDefaultTimestamp()
            )
        }
    }

    private suspend fun updatePlayingQueue(startPositionMs: Long = DEFAULT_POSITION_MS) {
//        val songs = getPlayingQueueUseCase().first()
//        val startIndex = getPlayingQueueIndexUseCase().first()
//        mediaBrowser?.run {
//            setMediaItems(songs.map(SongModel::asMediaItem), startIndex, startPositionMs)
//            prepare()
//        }
    }

    private fun updatePlayingQueueIndex(player: Player) = coroutineScope.launch {
        //   setPlayingQueueIndexUseCase(player.currentMediaItemIndex)
    }
}
