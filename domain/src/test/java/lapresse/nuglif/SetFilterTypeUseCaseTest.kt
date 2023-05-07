package lapresse.nuglif

import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import lapresse.domain.SetFilterTypeUseCase
import lapresse.domain.entity.FilterNewsEntity
import lapresse.domain.repository.UserPreferencesRepository
import org.junit.Test

import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class SetFilterTypeUseCaseTest {

    private val userPreferencesRepository: UserPreferencesRepository = mockk()

    private val useCase = SetFilterTypeUseCase(userPreferencesRepository)

    @Test
    fun `Given DATE FilterNewsEntity, then useCase shoull call UserPreferencesRepository with CHANNEL FilterNewsEntity`() =
        runTest(UnconfinedTestDispatcher()) {
            // Given
            coJustRun { userPreferencesRepository.setFilterType(any()) }
            val givenFilterEntity = FilterNewsEntity.DATE
            // When
            useCase.invoke(filterNewsEntity = givenFilterEntity)
            // Then
            val expectedEntity = FilterNewsEntity.CHANNEL
            coVerify {
                userPreferencesRepository.setFilterType(expectedEntity)
            }
        }

    @Test
    fun `Given CHANNEL FilterNewsEntity, then useCase shoull call UserPreferencesRepository with DATE FilterNewsEntity`() =
        runTest(UnconfinedTestDispatcher()) {
            // Given
            coJustRun { userPreferencesRepository.setFilterType(any()) }
            val givenFilterEntity = FilterNewsEntity.CHANNEL
            // When
            useCase.invoke(filterNewsEntity = givenFilterEntity)
            // Then
            val expectedEntity = FilterNewsEntity.DATE
            coVerify {
                userPreferencesRepository.setFilterType(expectedEntity)
            }
        }
}
