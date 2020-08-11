package br.com.lidiomar.flags.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.lidiomar.flags.model.local.dao.CountryDao
import br.com.lidiomar.flags.model.data.Country

@Database(entities = arrayOf(Country::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            //https://proandroiddev.com/synchronization-and-thread-safety-techniques-in-java-and-kotlin-f63506370e6d
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()

                INSTANCE = instance

                return instance
            }
        }

    }

}