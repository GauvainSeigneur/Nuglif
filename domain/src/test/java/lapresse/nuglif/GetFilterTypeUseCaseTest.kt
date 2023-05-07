package lapresse.nuglif

import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import lapresse.domain.GetFilterTypeUseCase
import lapresse.domain.GetNewsUseCase
import lapresse.domain.SetFilterTypeUseCase
import lapresse.domain.entity.ArticleEntity
import lapresse.domain.entity.FilterNewsEntity
import lapresse.domain.repository.NewsRepository
import lapresse.domain.repository.UserPreferencesRepository
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class GetFilterTypeUseCaseTest {

    private val userPreferencesRepository: UserPreferencesRepository = mockk()

    private val useCase = GetFilterTypeUseCase(userPreferencesRepository)

    @Test
    fun `Given userPreferencesRepository returs a flow, then useCase should returns this flow`() = runTest(UnconfinedTestDispatcher()) {
        // Given
        val givenFilterEntity = FilterNewsEntity.DATE
        val givenFlow = flowOf(givenFilterEntity)
        every { userPreferencesRepository.getFilterType() } returns givenFlow
        // When
        val filterTypeFlowValue = mutableListOf<FilterNewsEntity>()
        val collectJob = launch {
            useCase.invoke().toList(filterTypeFlowValue)
        }
        // Then
        assertEquals(1, filterTypeFlowValue.size)
        assertEquals(filterTypeFlowValue[0], givenFilterEntity)
        collectJob.cancelAndJoin()
    }
}
