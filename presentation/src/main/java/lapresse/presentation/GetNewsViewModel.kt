package lapresse.presentation

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lapresse.domain.GetFilterTypeUseCase
import lapresse.domain.GetNewsUseCase
import lapresse.domain.SetFilterTypeUseCase
import lapresse.presentation.action.UiInputHandler
import lapresse.presentation.mapper.NewsUiModelMapper
import lapresse.presentation.model.NewsUiInput
import lapresse.presentation.model.NewsUiModel

class GetNewsViewModel @AssistedInject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val getFilterTypeUseCase: GetFilterTypeUseCase,
    private val setFilterTypeUseCase: SetFilterTypeUseCase,
    private val newsUiModelMapper: NewsUiModelMapper,
    @Assisted private val scope: CoroutineScope,
) : UiInputHandler<NewsUiInput> {

    val uiModel: StateFlow<NewsUiModel> = combine(
        flow {
            emit(getNewsUseCase())
        },
        getFilterTypeUseCase()
    ) { news, filter ->
        news
        newsUiModelMapper.toUiModel(news, filter)
    }.stateIn(scope, SharingStarted.Eagerly, NewsUiModel.Loading)

    private fun lol() {
        combine(
            flow {
                emit(getNewsUseCase())
            },
            getFilterTypeUseCase()
        ) { news, filter ->
            news
            newsUiModelMapper.toUiModel(news, filter)
        }.stateIn(scope, SharingStarted.Eagerly, NewsUiModel.Loading)
    }

    override fun handleUiInputEvent(event: NewsUiInput) {
        when (event) {
            is NewsUiInput.Click -> {
            }

            is NewsUiInput.Filter -> {
            }
        }
    }
}