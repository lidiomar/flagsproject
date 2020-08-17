package br.com.lidiomar.flags.model.repository.remote

import br.com.lidiomar.flags.model.data.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountriesAll {
    @GET("all")
    suspend fun getAllCountries(): Response<List<Country>>
}