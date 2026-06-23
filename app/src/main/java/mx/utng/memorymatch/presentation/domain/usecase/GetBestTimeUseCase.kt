package mx.utng.memorymatch.presentation.domain.usecase

import mx.utng.memorymatch.presentation.domain.repository.BestTimeRepository

class GetBestTimeUseCase(private val repository: BestTimeRepository) {
    suspend operator fun invoke(): Long {
        return repository.getBestTime()
    }
}
