package mx.utng.memorymatch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.utng.memorymatch.presentation.presentation.MemoryViewModelFactory
import mx.utng.memorymatch.presentation.presentation.board.BoardScreen
import mx.utng.memorymatch.presentation.presentation.board.MemoryViewModel
import mx.utng.memorymatch.presentation.theme.MemoryMatchWearTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemoryMatchWearTheme {
                val vm: MemoryViewModel = viewModel(
                    factory = MemoryViewModelFactory(applicationContext),
                )
                BoardScreen(viewModel = vm)
            }
        }
    }
}
