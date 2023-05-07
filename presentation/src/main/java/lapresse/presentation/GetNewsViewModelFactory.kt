package lapresse.presentation

import dagger.assisted.AssistedFactory
import kotlinx.coroutines.CoroutineScope

@AssistedFactory
interface GetNewsViewModelFactory {
    fun create(
        viewModelScope: CoroutineScope,
    ): GetNewsViewModel
}
