package mx.utng.memorymatch.presentation.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.utng.memorymatch.presentation.data.datasourse.BestTimeDataSource
import mx.utng.memorymatch.presentation.data.repository.BestTimeRepositoryImpl
import mx.utng.memorymatch.presentation.domain.usecase.*
import mx.utng.memorymatch.presentation.presentation.board.BoardScreen
import mx.utng.memorymatch.presentation.presentation.board.MemoryViewModel

class MemoryViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dataSource = BestTimeDataSource(context)
        val repository = BestTimeRepositoryImpl(dataSource)
        return MemoryViewModel(
            shuffleBoard = ShuffleBoardUseCase(),
            flipCard = FlipCardUseCase(),
            checkMatch = CheckMatchUseCase(),
            saveBestTime = SaveBestTimeUseCase(repository),
            getBestTime = GetBestTimeUseCase(repository),
        ) as T
    }
}
