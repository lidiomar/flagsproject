package br.com.lidiomar.flags.model.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val retrofit =  Retrofit.Builder()
            .baseUrl("https://restcountries.eu/rest/v2/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    fun<T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}