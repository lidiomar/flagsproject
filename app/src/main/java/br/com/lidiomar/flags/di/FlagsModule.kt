package br.com.lidiomar.flags.di

import br.com.lidiomar.flags.model.repository.local.CountryLocalDataSource
import br.com.lidiomar.flags.model.repository.remote.CountryRemoteDataSource
import br.com.lidiomar.flags.model.repository.CountryDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class LocalDataSource

@Qualifier
annotation class RemoteDataSource

@InstallIn(ApplicationComponent::class)
@Module
abstract class FlagsLocalModule {
    @Singleton
    @Binds
    @LocalDataSource
    abstract fun bindLocalDataSource(impl: CountryLocalDataSource): CountryDataSource
}


@InstallIn(ApplicationComponent::class)
@Module
abstract class FlagsRemoteModule {
    @Singleton
    @Binds
    @RemoteDataSource
    abstract fun bindRemoteDataSource(impl: CountryRemoteDataSource): CountryDataSource
}