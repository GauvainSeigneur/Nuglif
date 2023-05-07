package lapresse.domain

import lapresse.domain.entity.ArticleEntity
import lapresse.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) : suspend () -> Result<List<ArticleEntity>> {

    override suspend fun invoke(): Result<List<ArticleEntity>> = runCatching {
        newsRepository.getNews()
    }
}