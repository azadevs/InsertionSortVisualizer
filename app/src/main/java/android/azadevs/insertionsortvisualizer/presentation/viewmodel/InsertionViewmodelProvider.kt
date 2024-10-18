package android.azadevs.insertionsortvisualizer.presentation.viewmodel

import android.azadevs.insertionsortvisualizer.domain.GetInsertionSortUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by : Azamat Kalmurzaev
 * 18/10/24
 */
class InsertionViewmodelProvider(
    private val getInsertionSortUseCase: GetInsertionSortUseCase = GetInsertionSortUseCase()
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InsertionSortViewModel(getInsertionSortUseCase) as T
    }
}