package br.com.lidiomar.flags.model.repository

import br.com.lidiomar.flags.di.LocalDataSource
import br.com.lidiomar.flags.di.RemoteDataSource
import br.com.lidiomar.flags.model.data.Country
import br.com.lidiomar.flags.utils.DataResource
import javax.inject.Inject

class MainRepository @Inject constructor() {
    @Inject
    @LocalDataSource
    lateinit var localDataSource: CountryDataSource

    @Inject
    @RemoteDataSource
    lateinit var remoteDataSource: CountryDataSource

    suspend fun getAllCountries(): DataResource<List<Country>> {
        val localCountries = localDataSource.getAllCountries()
        if (localCountries.status == DataResource.Status.SUCCESS) {
            return localCountries
        }
        return remoteDataSource.getAllCountries()
    }
}