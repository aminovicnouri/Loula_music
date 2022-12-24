package com.aminovic.loula.domain.use_case

import com.aminovic.loula.domain.repository.SettingsRepository
import javax.inject.Inject

class SetPlayingQueueIndexUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
    suspend operator fun invoke(index: Int) = settingsRepository.setPlayingQueueIndex(index)
}
