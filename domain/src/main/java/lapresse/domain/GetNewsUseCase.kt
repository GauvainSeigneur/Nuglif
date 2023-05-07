package lapresse.domain

import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) : suspend () -> Result<List<ArticleEntity>> {

    override suspend fun invoke(): Result<List<ArticleEntity>> = runCatching {
        newsRepository.getNews()
    }
}