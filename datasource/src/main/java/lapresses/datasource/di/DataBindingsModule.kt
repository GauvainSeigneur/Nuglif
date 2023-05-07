package lapresses.datasource.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import lapresse.domain.repository.NewsRepository
import lapresse.domain.repository.UserPreferencesRepository
import lapresses.datasource.NewsDataRepository
import lapresses.datasource.UserPreferencesDataRepository

@InstallIn(ViewModelComponent::class)
@Module
abstract class DataBindingsModule {
    @Binds
    abstract fun bindNewsRepository(impl: NewsDataRepository): NewsRepository

    @Binds
    abstract fun bindUserPreferencesRepository(impl: UserPreferencesDataRepository): UserPreferencesRepository

}