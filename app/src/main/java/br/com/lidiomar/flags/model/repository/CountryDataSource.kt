package br.com.lidiomar.flags.model.repository

import br.com.lidiomar.flags.model.data.Country
import br.com.lidiomar.flags.utils.DataResource

interface CountryDataSource {
    suspend fun getAllCountries(): DataResource<List<Country>>
    suspend fun addCountry(country: Country)
    suspend fun removeCountries()
}