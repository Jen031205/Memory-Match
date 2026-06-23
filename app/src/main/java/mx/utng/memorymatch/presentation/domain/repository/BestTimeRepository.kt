package mx.utng.memorymatch.presentation.domain.repository

interface BestTimeRepository {
    suspend fun getBestTime(): Long
    suspend fun saveBestTime(seconds: Long)
}
