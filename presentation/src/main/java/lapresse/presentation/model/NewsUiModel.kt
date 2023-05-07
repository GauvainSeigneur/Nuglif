package lapresse.presentation.model

sealed interface NewsUiModel {
    object Loading : NewsUiModel
    data class Success(val news: List<NewsItemUiModel>) : NewsUiModel
    object Error : NewsUiModel
}
