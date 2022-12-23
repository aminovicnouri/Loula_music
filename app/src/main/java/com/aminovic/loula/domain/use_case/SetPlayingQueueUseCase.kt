package com.aminovic.loula.domain.use_case

import com.aminovic.loula.data.modal.SongModel
import com.aminovic.loula.domain.repository.SongRepository
import javax.inject.Inject

class SetPlayingQueueUseCase @Inject constructor(private val songRepository: SongRepository) {
    suspend operator fun invoke(songs: List<SongModel>) = songRepository.setPlayingQueue(songs)
}
