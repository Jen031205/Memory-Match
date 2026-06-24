package mx.utng.memorymatch.presentation.presentation.board

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import mx.utng.memorymatch.presentation.domain.model.GameState

@Composable
fun VictoryScreen(state: GameState, onRestart: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("¡Victoria!", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Green)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Tiempo: ${state.elapsedSeconds}s", fontSize = 14.sp)
        Text("Movimientos: ${state.moves}", fontSize = 14.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onRestart) {
            Text("Reiniciar")
        }
    }
}
