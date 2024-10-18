package android.azadevs.insertionsortvisualizer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by : Azamat Kalmurzaev
 * 18/10/24
 */
@Composable
fun VisualizerSection(
    modifier: Modifier = Modifier,
    numbers: IntArray
) {
    BoxWithConstraints(
        modifier = modifier.padding(horizontal = 4.dp)
    ) {
        val maxHeight = maxHeight - 100.dp
        val itemWidth = remember {
            maxWidth / numbers.size - 8.dp
        }
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            numbers.forEach { number ->
                Box(
                    modifier = Modifier
                        .width(itemWidth)
                        .height(if (number.dp > maxHeight) maxHeight else (number.dp))
                        .background(
                            MaterialTheme.colorScheme.secondary
                        )
                )

            }

        }
    }

}