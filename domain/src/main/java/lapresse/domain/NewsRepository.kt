package lapresse.domain

interface NewsRepository {
    suspend fun getNews(): List<ArticleEntity>
}