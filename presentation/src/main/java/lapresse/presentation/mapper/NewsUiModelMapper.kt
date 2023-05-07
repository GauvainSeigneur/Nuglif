package lapresse.presentation.mapper

import lapresse.domain.entity.ArticleEntity
import lapresse.domain.entity.FilterNewsEntity
import lapresse.presentation.action.UiInputHandler
import lapresse.presentation.extension.sortedWithLocaleBy
import lapresse.presentation.model.NewsItemUiModel
import lapresse.presentation.model.NewsUiInput
import lapresse.presentation.model.NewsUiModel
import lapresse.presentation.provider.LocalProvider
import javax.inject.Inject

class NewsUiModelMapper @Inject constructor(
    private val localProvider: LocalProvider,
    private val newsItemUiMapper: NewsItemUiMapper,
    private val filterImageUiMapper: FilterImageUiMapper,
) {

    fun toUiModel(
        result: Result<List<ArticleEntity>>,
        filterNewsEntity: FilterNewsEntity,
        uiInputHandler: UiInputHandler<NewsUiInput>,
    ): NewsUiModel = result.fold(
        onSuccess = { news ->
            NewsUiModel.Success(
                news = mapAndSort(news, filterNewsEntity),
                filterUIModel = filterImageUiMapper.toUiModel(filterNewsEntity, uiInputHandler)
            )
        },
        onFailure = {
            NewsUiModel.Error
        }
    )

    private fun mapAndSort(news: List<ArticleEntity>, filterNewsEntity: FilterNewsEntity): List<NewsItemUiModel> {
        val sortedList = when (filterNewsEntity) {
            FilterNewsEntity.CHANNEL -> news.sortedWithLocaleBy(localProvider.getLocal()) { newSItem ->
                newSItem.channelName
            }

            FilterNewsEntity.DATE -> news.sortedByDescending { newSItem ->
                newSItem.publicationDate
            }
        }

        return sortedList
            .map { item ->
                newsItemUiMapper.toUiModel(item)
            }
    }
}
