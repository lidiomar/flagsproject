package br.com.lidiomar.flags.model.repository

import android.app.Application
import br.com.lidiomar.flags.model.data.Country
import br.com.lidiomar.flags.model.local.AppDatabase
import br.com.lidiomar.flags.model.remote.CountriesAll
import br.com.lidiomar.flags.model.remote.ServiceBuilder
import br.com.lidiomar.flags.utils.DataResource

class MainRepository(application: Application) {
    private lateinit var request: CountriesAll
    private var appDataBase = AppDatabase.getDatabase(application)

    suspend fun getAllCountries(): DataResource<List<Country>> {
        val localCountries = getLocalCountries()
        if (localCountries.status == DataResource.Status.SUCCESS) {
            return localCountries
        }
        return getRemoteCountriesAndInsertIntoLocalDatabase()
    }

    private suspend fun getLocalCountries(): DataResource<List<Country>> {
        val countries = appDataBase.countryDao().getCountries()
        if (countries.isNotEmpty()){
            return DataResource.success(countries)
        }
        return DataResource.error("Local database is empty")
    }

    private suspend fun insertCountries(countries: List<Country>) {
        for(country in countries) {
            appDataBase.countryDao().insertCountry(country)
        }
    }

    private  suspend fun getRemoteCountriesAndInsertIntoLocalDatabase(): DataResource<List<Country>> {
        request = ServiceBuilder.buildService(CountriesAll::class.java)
        val countries = request.getAllCountries()
        if (countries.isSuccessful) {
            countries.body()?.let { countryList ->
                insertCountries(countryList)
                return DataResource.success(countryList)
            }
        }
        return DataResource.error("Error from server")
    }

}