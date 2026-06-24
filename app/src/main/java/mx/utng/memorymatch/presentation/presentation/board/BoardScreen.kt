package mx.utng.memorymatch.presentation.presentation.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.*
import kotlinx.coroutines.delay
import mx.utng.memorymatch.presentation.domain.model.GamePhase
import mx.utng.memorymatch.presentation.domain.model.GameState

@Composable
fun BoardScreen(viewModel: MemoryViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    val haptic = LocalHapticFeedback.current
    val gridState = rememberLazyGridState()

    // Efectos de una sola vez (haptics)
    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                GameEffect.HapticMatch   -> haptic.performHapticFeedback(
                    HapticFeedbackType.LongPress)
                GameEffect.HapticMiss    -> haptic.performHapticFeedback(
                    HapticFeedbackType.LongPress)
                GameEffect.HapticVictory -> repeat(3) {  // triple vibración
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    delay(150L)
                }
            }
        }
    }

    if (state.phase == GamePhase.WON) {
        VictoryScreen(state = state, onRestart = viewModel::startNewGame)
        return
    }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF0A0A1E))) {

        // HUD superior: tiempo (Sin scrollAway por incompatibilidad con LazyGridState)
        TimeText {
            curvedText(" • ${state.elapsedSeconds}s • ${state.moves} m")
        }

        // Cuadrícula 3×4 centrada en la pantalla circular
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            state = gridState,
            modifier = Modifier
                .padding(top = 28.dp, start = 8.dp, end = 8.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement   = Arrangement.spacedBy(6.dp),
        ) {
            itemsIndexed(state.board) { index, card ->
                CardItem(
                    card  = card,
                    onTap = { viewModel.onCardTapped(index) },
                )
            }
        }

        // Indicador de progreso circular (pares encontrados)
        CircularProgressIndicator(
            progress = state.matchesFound.toFloat() / GameState.TOTAL_PAIRS,
            modifier = Modifier.fillMaxSize(),
            startAngle = 295f,
            endAngle = 245f,
            strokeWidth = 3.dp,
            indicatorColor = Color(0xFFF9A825),
            trackColor = Color.White.copy(alpha = 0.1f)
        )
    }
}
