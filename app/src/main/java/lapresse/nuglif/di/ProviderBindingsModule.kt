package lapresse.nuglif.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lapresse.nuglif.provider.AndroidLocalProvider
import lapresse.presentation.provider.LocalProvider

@InstallIn(SingletonComponent::class)
@Module
abstract class ProviderBindingsModule {
    @Binds
    abstract fun bindLocalProvider(impl: AndroidLocalProvider): LocalProvider
}