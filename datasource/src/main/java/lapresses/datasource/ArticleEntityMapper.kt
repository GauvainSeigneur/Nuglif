package lapresses.datasource

import lapresse.domain.entity.ArticleEntity
import javax.inject.Inject

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
                publicationDate = articleDataItem.publicationDate
            )
        }
}