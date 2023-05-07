package lapresse.data

import lapresse.domain.ArticleEntity
import lapresse.domain.NewsRepository

class NewsDataRepository: NewsRepository {
    override suspend fun getNews(): List<ArticleEntity> {

    }
}