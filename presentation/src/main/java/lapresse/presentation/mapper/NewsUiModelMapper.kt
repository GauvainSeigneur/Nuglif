package lapresse.presentation.mapper

import lapresse.domain.ArticleEntity
import lapresse.presentation.model.NewsUiModel
import javax.inject.Inject

class NewsUiModelMapper @Inject constructor(private val newsItemUiMapper: NewsItemUiMapper) {

    fun toUiModel(result: Result<List<ArticleEntity>>): NewsUiModel = result.fold(
        onSuccess = { news ->
            NewsUiModel.Success(
                news = news.map { item ->
                    newsItemUiMapper.toUiModel(item)
                }
            )
        },
        onFailure = {
            NewsUiModel.Error
        }
    )
}