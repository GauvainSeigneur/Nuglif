package lapresse.presentation.model

sealed interface NewsUiModel {
    object Loading : NewsUiModel
    data class Success(
        val news: List<NewsItemUiModel>,
        val filterUIModel: ImageUiModel
        ) : NewsUiModel
    object Error : NewsUiModel
}
