package lapresses.datasource

import lapresse.domain.ArticleEntity
import lapresse.domain.NewsRepository
import lapresses.datasource.source.NewsSource
import javax.inject.Inject

class NewsDataRepository @Inject constructor(
    private val newsSource: NewsSource,
    private val articleEntityMapper: ArticleEntityMapper,
) : NewsRepository {
    override suspend fun getNews(): List<ArticleEntity> = articleEntityMapper.toEntities(newsSource.getNews())
}