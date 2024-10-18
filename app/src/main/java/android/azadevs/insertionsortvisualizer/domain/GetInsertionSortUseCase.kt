package android.azadevs.insertionsortvisualizer.domain

/**
 * Created by : Azamat Kalmurzaev
 * 18/10/24
 */
class GetInsertionSortUseCase {

    operator fun invoke(
        numbers: IntArray,
        onSwap: (IntArray) -> Unit,
        onFinish: () -> Unit
    ) {
        for (i in 1 until numbers.size) {
            val key = numbers[i]
            var j = i - 1
            while (j >= 0 && numbers[j] > key) {
                numbers[j + 1] = numbers[j]
                onSwap(numbers)
                j--
            }
            numbers[j + 1] = key
            onSwap(numbers)
        }
        onFinish()
    }
}