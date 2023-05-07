package lapresse.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import lapresse.domain.entity.FilterNewsEntity
import lapresse.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class GetFilterTypeUseCase @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) : () -> Flow<FilterNewsEntity> {

    override fun invoke(): Flow<FilterNewsEntity> = userPreferencesRepository.getFilterType().map { filter ->
        filter ?: FilterNewsEntity.DATE
    }
}