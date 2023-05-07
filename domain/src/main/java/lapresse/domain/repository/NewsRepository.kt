package lapresse.domain.repository

import lapresse.domain.entity.ArticleEntity

interface NewsRepository {
    suspend fun getNews(): List<ArticleEntity>
}