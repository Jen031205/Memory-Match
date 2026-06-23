package mx.utng.memorymatch.presentation.domain.usecase

import mx.utng.memorymatch.presentation.domain.model.GameState
import mx.utng.memorymatch.presentation.domain.model.GamePhase
import mx.utng.memorymatch.presentation.domain.model.Card

class CheckMatchUseCase {
    /**
     * Evalúa si las dos tarjetas seleccionadas coinciden.
     * Si coinciden, se marcan como encontradas.
     * Si no, se vuelven a ocultar.
     */
    operator fun invoke(state: GameState): GameState {
        val firstIdx = state.firstSelected ?: return state
        val secondIdx = state.secondSelected ?: return state

        val card1 = state.board[firstIdx]
        val card2 = state.board[secondIdx]

        return if (card1.symbol == card2.symbol) {
            // Es un par!
            val newBoard = state.board.mapIndexed { i, c ->
                if (i == firstIdx || i == secondIdx) c.copy(isMatched = true) else c
            }
            val newMatches = state.matchesFound + 1
            state.copy(
                board = newBoard,
                matchesFound = newMatches,
                firstSelected = null,
                secondSelected = null,
                phase = if (newMatches == GameState.TOTAL_PAIRS) GamePhase.WON else GamePhase.IDLE
            )
        } else {
            // No es par, se voltean de nuevo
            val newBoard = state.board.mapIndexed { i, c ->
                if (i == firstIdx || i == secondIdx) c.copy(isFlipped = false) else c
            }
            state.copy(
                board = newBoard,
                firstSelected = null,
                secondSelected = null,
                phase = GamePhase.IDLE
            )
        }
    }
}
