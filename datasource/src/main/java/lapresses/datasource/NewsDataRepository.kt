package lapresses.datasource

import lapresse.domain.entity.ArticleEntity
import lapresse.domain.repository.NewsRepository
import lapresses.datasource.source.NewsSource
import javax.inject.Inject

class NewsDataRepository @Inject constructor(
    private val newsSource: NewsSource,
    private val articleEntityMapper: ArticleEntityMapper,
) : NewsRepository {
    override suspend fun getNews(): List<ArticleEntity> = articleEntityMapper.toEntities(newsSource.getNews())
}