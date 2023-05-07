package lapresse.nuglif

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import lapresse.domain.GetNewsUseCase
import lapresse.domain.entity.ArticleEntity
import lapresse.domain.repository.NewsRepository
import org.junit.Test

import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class GetNewsUseCaseTest {

    private val newsRepository: NewsRepository = mockk()

    private val useCase = GetNewsUseCase(newsRepository)

    @Test
    fun `Given newsRepository returs a list, then useCase should returns a Result`() = runTest(UnconfinedTestDispatcher()) {
        val giveArticleEntity = ArticleEntity(
            id = "givenId",
            channelName = "channelName",
            title = "title",
            publicationDate = null,
            imageUrl = ""
        )
        val givenList = listOf(giveArticleEntity)
        coEvery { newsRepository.getNews() } returns givenList
        val actual = useCase.invoke()
        val expected = Result.success(givenList)
        assertEquals(expected, actual)
    }

    @Test
    fun `Given newsRepository throw an error, then useCase should returns a Result`() = runTest(UnconfinedTestDispatcher()) {
        val givenError = Throwable("oups")
        coEvery { newsRepository.getNews() } throws givenError
        val actual = useCase.invoke()
        val expected = Result.failure<List<ArticleEntity>>(givenError)
        assertEquals(expected, actual)
    }
}
