package lapresses.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import lapresse.domain.entity.FilterNewsEntity
import lapresse.domain.repository.UserPreferencesRepository
import javax.inject.Inject

private val FILTER_TYPE_KEY = stringPreferencesKey("FILTER_TYPE_KEY")

class UserPreferencesDataRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : UserPreferencesRepository {

    override fun getFilterType(): Flow<FilterNewsEntity?> = dataStore.data.map { preferences ->
        preferences[FILTER_TYPE_KEY]?.let {
            try {
                FilterNewsEntity.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }

    override suspend fun setFilterType(filterNewsEntity: FilterNewsEntity) {
        dataStore.edit { preferences ->
            preferences[FILTER_TYPE_KEY] = filterNewsEntity.name
        }
    }
}