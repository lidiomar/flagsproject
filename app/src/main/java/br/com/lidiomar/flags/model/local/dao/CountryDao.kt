package br.com.lidiomar.flags.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.lidiomar.flags.model.data.Country

@Dao
interface CountryDao {
    @Query("SELECT * FROM country_table")
    suspend fun getCountries(): List<Country>

    @Insert
    suspend fun insertCountry(country: Country)

    @Query("DELETE FROM country_table")
    suspend fun deleteCountries()
}