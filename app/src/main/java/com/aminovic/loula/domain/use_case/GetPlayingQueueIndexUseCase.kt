package com.aminovic.loula.domain.use_case

import com.aminovic.loula.domain.repository.SettingsRepository
import javax.inject.Inject

class GetPlayingQueueIndexUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
    operator fun invoke() = settingsRepository.getPlayingQueueIndex()
}
