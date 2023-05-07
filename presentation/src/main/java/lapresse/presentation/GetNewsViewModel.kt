package lapresse.presentation

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lapresse.domain.GetNewsUseCase
import lapresse.presentation.mapper.NewsUiModelMapper
import lapresse.presentation.model.NewsUiModel

class GetNewsViewModel @AssistedInject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val newsUiModelMapper: NewsUiModelMapper,
    @Assisted private val scope: CoroutineScope,
) {

    private val mutableUiState = MutableStateFlow<NewsUiModel>(NewsUiModel.Loading)

    val uiModel: StateFlow<NewsUiModel> = mutableUiState

    init {
        scope.launch(Dispatchers.IO) {
            mutableUiState.update {
                newsUiModelMapper.toUiModel(getNewsUseCase())
            }
        }
    }


}