package lapresses.datasource

import lapresse.domain.entity.ArticleEntity
import javax.inject.Inject

private const val FALLBACK_URL = "https://via.placeholder.com/600x400"

class ArticleEntityMapper @Inject constructor() {

    fun toEntities(dataItems: List<ArticleDataItem>): List<ArticleEntity> = dataItems.mapNotNull { articleDataItem ->
        toEntity(articleDataItem)
    }

    private fun toEntity(articleDataItem: ArticleDataItem): ArticleEntity? =
        articleDataItem.id?.let { articleId ->
            ArticleEntity(
                id = articleId,
                channelName = articleDataItem.channelName,
                title = articleDataItem.title,
                publicationDate = articleDataItem.publicationDate,
                imageUrl = articleDataItem.visual.firstOrNull()?.urlPattern?.let { url  ->
                    url.replace("http:", "https:")
                }?:FALLBACK_URL
            )
        }
}