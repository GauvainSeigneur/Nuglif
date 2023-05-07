package lapresse.domain.repository

import kotlinx.coroutines.flow.Flow
import lapresse.domain.entity.FilterNewsEntity

interface UserPreferencesRepository {
    fun getFilterType(): Flow<FilterNewsEntity?>
    suspend fun setFilterType(filterNewsEntity: FilterNewsEntity)
}