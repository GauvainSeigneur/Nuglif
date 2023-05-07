package lapresse.presentation.mapper

import lapresse.domain.entity.ArticleEntity
import lapresse.presentation.model.ImageUiModel
import lapresse.presentation.model.LocalImageUiModel
import lapresse.presentation.model.NewsItemUiModel
import javax.inject.Inject

private const val UNKNOWN = "Unknown"

class NewsItemUiMapper @Inject constructor() {
    fun toUiModel(item: ArticleEntity) = NewsItemUiModel(
        id = item.id,
        title = item.title ?: UNKNOWN,
        channelName = item.channelName ?: UNKNOWN,
        publicationDate = item.publicationDate ?: UNKNOWN,
        image = ImageUiModel.Remote(
            url = item.imageUrl,
            fallback = LocalImageUiModel.FALLBACK
        )
    )
}
