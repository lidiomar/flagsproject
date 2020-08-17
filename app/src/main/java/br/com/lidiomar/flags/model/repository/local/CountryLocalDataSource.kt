package br.com.lidiomar.flags.model.repository.local

import br.com.lidiomar.flags.model.data.Country
import br.com.lidiomar.flags.model.repository.local.dao.CountryDao
import br.com.lidiomar.flags.model.repository.CountryDataSource
import br.com.lidiomar.flags.utils.DataResource
import javax.inject.Inject

class CountryLocalDataSource @Inject constructor(private val countryDao: CountryDao):
    CountryDataSource {

    override suspend  fun getAllCountries(): DataResource<List<Country>> {
        val countries = countryDao.getCountries()
        if (countries.isNotEmpty()){
            return DataResource.success(countries)
        }
        return DataResource.error("Local database is empty")
    }

    override suspend  fun addCountry(country: Country) {
        countryDao.insertCountry(country)
    }

    override suspend  fun removeCountries() {
        countryDao.deleteCountries()
    }
}