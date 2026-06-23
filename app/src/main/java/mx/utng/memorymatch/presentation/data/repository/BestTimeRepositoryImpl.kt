package mx.utng.memorymatch.presentation.data.repository

import mx.utng.memorymatch.presentation.data.datasourse.BestTimeDataSource
import mx.utng.memorymatch.presentation.domain.repository.BestTimeRepository

class BestTimeRepositoryImpl(private val ds: BestTimeDataSource) : BestTimeRepository {
    override suspend fun getBestTime() = ds.getBestTime()
    override suspend fun saveBestTime(s: Long) = ds.saveBestTime(s)
}
