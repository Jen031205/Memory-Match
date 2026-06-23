package mx.utng.memorymatch.presentation.domain.usecase

import mx.utng.memorymatch.presentation.domain.repository.BestTimeRepository

class SaveBestTimeUseCase(private val repository: BestTimeRepository) {
    suspend operator fun invoke(seconds: Long) {
        repository.saveBestTime(seconds)
    }
}
