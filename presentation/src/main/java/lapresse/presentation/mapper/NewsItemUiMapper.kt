package lapresse.presentation.mapper

import lapresse.domain.ArticleEntity
import lapresse.presentation.model.NewsItemUiModel
import javax.inject.Inject

class NewsItemUiMapper @Inject constructor() {
    fun toUiModel(item : ArticleEntity) = NewsItemUiModel(
        id = item.id,
        title = item.title?:"Unknown",
    )
}