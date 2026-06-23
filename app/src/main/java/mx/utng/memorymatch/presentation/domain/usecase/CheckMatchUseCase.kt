package mx.utng.memorymatch.presentation.domain.usecase

import mx.utng.memorymatch.presentation.domain.model.GameState

enum class MatchResult {
    HIT, MISS, PENDING
}

class CheckMatchUseCase {
    operator fun invoke(state: GameState): MatchResult {
        val firstIdx = state.firstSelected ?: return MatchResult.PENDING
        val secondIdx = state.secondSelected ?: return MatchResult.PENDING

        val card1 = state.board[firstIdx]
        val card2 = state.board[secondIdx]

        return if (card1.symbol == card2.symbol) {
            MatchResult.HIT
        } else {
            MatchResult.MISS
        }
    }
}
