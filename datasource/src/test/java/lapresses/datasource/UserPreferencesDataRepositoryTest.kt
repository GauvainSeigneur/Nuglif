package lapresses.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import lapresse.domain.entity.FilterNewsEntity
import org.junit.Assert
import org.junit.Test

class UserPreferencesDataRepositoryTest {

    private val dataStore: DataStore<Preferences> = mockk()
    private val repository = UserPreferencesDataRepository(dataStore)

    @Test
    fun `Given dataStore returns a valid name, when repository getFilterType is called, then it should return a filtered flow`() =
        runTest(UnconfinedTestDispatcher()) {
            // Given
            val givenEntity = FilterNewsEntity.CHANNEL
            val givenPreference: Preferences = mockk()
            every { givenPreference.get<String>(key = any()) } returns givenEntity.name
            every { dataStore.data } returns flowOf(givenPreference)
            // When
            val filterTypeFlowValue = mutableListOf<FilterNewsEntity?>()
            val collectJob = launch {
                repository.getFilterType().toList(filterTypeFlowValue)
            }
            // Then
            Assert.assertEquals(1, filterTypeFlowValue.size)
            Assert.assertEquals(filterTypeFlowValue[0], givenEntity)
            collectJob.cancelAndJoin()
        }

    @Test
    fun `Given dataStore returns an unvalid name, when repository getFilterType is called, then it should return a null flow`() =
        runTest(UnconfinedTestDispatcher()) {
            // Given
            val givenPreference: Preferences = mockk()
            every { givenPreference.get<String>(key = any()) } returns "random"
            every { dataStore.data } returns flowOf(givenPreference)
            // When
            val filterTypeFlowValue = mutableListOf<FilterNewsEntity?>()
            val collectJob = launch {
                repository.getFilterType().toList(filterTypeFlowValue)
            }
            // Then
            Assert.assertEquals(1, filterTypeFlowValue.size)
            Assert.assertEquals(filterTypeFlowValue[0], null)
            collectJob.cancelAndJoin()
        }
}