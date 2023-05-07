package lapresse.domain

import lapresse.domain.entity.FilterNewsEntity
import lapresse.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class SetFilterTypeUseCase @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) :
    suspend (FilterNewsEntity) -> Unit {

    override suspend fun invoke(filterNewsEntity: FilterNewsEntity) {
        val newFilterEntity = when (filterNewsEntity) {
            FilterNewsEntity.DATE -> FilterNewsEntity.CHANNEL
            FilterNewsEntity.CHANNEL -> FilterNewsEntity.DATE
        }
        userPreferencesRepository.setFilterType(newFilterEntity)
    }
}