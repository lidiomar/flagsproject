package br.com.lidiomar.flags.model.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.lidiomar.flags.model.data.Country
import br.com.lidiomar.flags.model.repository.local.dao.CountryDao

@Database(entities = arrayOf(Country::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun countryDao(): CountryDao
}