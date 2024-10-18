package android.azadevs.insertionsortvisualizer.presentation.viewmodel

import android.azadevs.insertionsortvisualizer.domain.GetInsertionSortUseCase
import android.azadevs.insertionsortvisualizer.presentation.utils.UiEvent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by : Azamat Kalmurzaev
 * 18/10/24
 */
class InsertionSortViewModel(
    private val getInsertionSortUseCase: GetInsertionSortUseCase
) : ViewModel() {
    val isPlaying = mutableStateOf(false)

    private var sortedArray = mutableListOf<List<Int>>()

    private var time = 150L

    private var isPaused = false

    var numbers = mutableStateOf(generateRandomArray())

    private var sortingIndex = 0

    private var next = 1

    private var previous = 0

    init {
        viewModelScope.launch {
            getInsertionSortUseCase.invoke(
                numbers = numbers.value.clone(),
                onSwap = { modifiedArray ->
                    sortedArray.add(modifiedArray.toMutableList())
                },
                onFinish = {
                    isPlaying.value = false
                }
            )
        }
    }

    fun onEvent(event: UiEvent) {
        when (event) {
            UiEvent.Next -> next()
            UiEvent.PlayPause -> playPause()

            UiEvent.Previous -> previous()

            UiEvent.SlowDown -> slowDown()

            UiEvent.SpeedUp -> speedUp()
        }
    }

    private fun next() {
        if (next < sortedArray.size) {
            numbers.value = sortedArray[next].toIntArray()
            next++
            previous++
        }
    }


    private fun previous() {
        if (previous >= 0) {
            numbers.value = sortedArray[previous].toIntArray()
            next--
            previous--
        }
    }

    private fun playPause() {
        if (isPlaying.value) pause()
        else play()
        isPlaying.value = !isPlaying.value
    }

    private fun pause() {
        isPaused = true
    }

    private fun play() = viewModelScope.launch {
        isPaused = false
        for (i in sortingIndex until sortedArray.size) {
            if (!isPaused) {
                delay(time)
                numbers.value = sortedArray[i].toIntArray()
            } else {
                sortingIndex = i
                next = i + 1
                previous = i - 1
                return@launch
            }
        }
        isPlaying.value = false
    }

    private fun speedUp() {
        if (time >= 150) {
            time -= 50
        }
    }

    private fun slowDown() {
        time += 50
    }

    private fun generateRandomArray(): IntArray {
        return IntArray(size = 20) {
            (0..600).random()
        }
    }


}