package lapresses.datasource.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import lapresse.domain.repository.NewsRepository
import lapresses.datasource.NewsDataRepository

@InstallIn(ViewModelComponent::class)
@Module
abstract class DataBindingsModule {
    @Binds
    abstract fun bindNewsRepository(impl: NewsDataRepository): NewsRepository

}