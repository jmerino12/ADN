package com.example.adn.di

import android.app.Application
import androidx.room.Room
import com.example.adn.model.database.AppDatabase
import com.example.adn.model.database.RoomDataSource
import com.example.domainlibrary.repositories.LocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "Adn-db"
    ).build()

    @Provides
    fun localDataSourceProvider(db: AppDatabase): LocalDataSource = RoomDataSource(db)
}