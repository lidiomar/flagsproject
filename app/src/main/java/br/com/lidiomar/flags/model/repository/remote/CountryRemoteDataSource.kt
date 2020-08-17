package br.com.lidiomar.flags.model.repository.remote

import br.com.lidiomar.flags.model.data.Country
import br.com.lidiomar.flags.model.repository.local.CountryLocalDataSource
import br.com.lidiomar.flags.model.repository.CountryDataSource
import br.com.lidiomar.flags.utils.DataResource
import javax.inject.Inject

class CountryRemoteDataSource @Inject constructor(private val countriesAll: CountriesAll,
                                                  private val countryLocalDataSource: CountryLocalDataSource): CountryDataSource {
    override suspend fun getAllCountries(): DataResource<List<Country>> {
        val countries = countriesAll.getAllCountries()
        if (countries.isSuccessful) {
            countries.body()?.let { countryList ->
                insertCountries(countryList)
                return DataResource.success(countryList)
            }
        }
        return DataResource.error("Error from server")
    }

    override suspend fun addCountry(country: Country) {}

    override suspend fun removeCountries() {}

    private suspend fun insertCountries(countries: List<Country>) {
        for(country in countries) {
            countryLocalDataSource.addCountry(country)
        }
    }
}