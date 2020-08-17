package br.com.lidiomar.flags.di

import android.content.Context
import androidx.room.Room
import br.com.lidiomar.flags.model.repository.local.AppDatabase
import br.com.lidiomar.flags.model.repository.local.dao.CountryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "logging.db"
        ).build()
    }

    @Provides
    fun provideLogDao(dataBase: AppDatabase): CountryDao {
        return dataBase.countryDao()
    }
}