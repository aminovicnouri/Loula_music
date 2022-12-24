package com.aminovic.loula.domain.use_case

import com.aminovic.loula.domain.repository.SongRepository
import javax.inject.Inject

class GetPlayingQueueUseCase @Inject constructor(private val songRepository: SongRepository) {
    operator fun invoke() = songRepository.getPlayingQueue()
}
