package lapresse.presentation.mapper

import lapresse.domain.entity.ArticleEntity
import lapresse.domain.entity.FilterNewsEntity
import lapresse.presentation.model.NewsUiModel
import javax.inject.Inject

class NewsUiModelMapper @Inject constructor(
    private val newsItemUiMapper: NewsItemUiMapper,
    private val filterImageUiMapper: FilterImageUiMapper,
) {

    fun toUiModel(
        result: Result<List<ArticleEntity>>,
        filterNewsEntity: FilterNewsEntity,
    ): NewsUiModel = result.fold(
        onSuccess = { news ->
            NewsUiModel.Success(
                news = news.map { item ->
                    newsItemUiMapper.toUiModel(item)
                },
                filterUIModel = filterImageUiMapper.toUiModel(filterNewsEntity)
            )
        },
        onFailure = {
            NewsUiModel.Error
        }
    )
}