package android.azadevs.insertionsortvisualizer.presentation

import android.azadevs.insertionsortvisualizer.presentation.components.VisualizerBottomBar
import android.azadevs.insertionsortvisualizer.presentation.components.VisualizerSection
import android.azadevs.insertionsortvisualizer.presentation.utils.UiEvent
import android.azadevs.insertionsortvisualizer.presentation.viewmodel.InsertionSortViewModel
import android.azadevs.insertionsortvisualizer.presentation.viewmodel.InsertionViewmodelProvider
import android.azadevs.insertionsortvisualizer.ui.theme.InsertionSortVisualizerTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this, InsertionViewmodelProvider())[InsertionSortViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InsertionSortVisualizerTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column {
                        VisualizerSection(
                            numbers = viewModel.numbers.value,
                            modifier = Modifier.fillMaxWidth()
                        )
                        val isPlaying = viewModel.isPlaying.value
                        VisualizerBottomBar(
                            playPause = { viewModel.onEvent(UiEvent.PlayPause) },
                            slowDown = { viewModel.onEvent(UiEvent.SlowDown) },
                            speedUp = { viewModel.onEvent(UiEvent.SpeedUp) },
                            next = { viewModel.onEvent(UiEvent.Next) },
                            previous = { viewModel.onEvent(UiEvent.Previous) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            isPlaying = isPlaying
                        )
                    }
                }
            }
        }
    }
}