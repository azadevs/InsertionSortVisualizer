package android.azadevs.insertionsortvisualizer.presentation.utils

/**
 * Created by : Azamat Kalmurzaev
 * 18/10/24
 */
sealed class UiEvent {

    data object SlowDown : UiEvent()
    data object SpeedUp : UiEvent()
    data object PlayPause : UiEvent()
    data object Previous : UiEvent()
    data object Next : UiEvent()
}