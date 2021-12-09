package com.example.adn.di

import android.app.Application
import androidx.room.Room
import com.example.domain.repositories.CarRepository
import com.example.domain.repositories.MotorcycleRepository
import com.example.infraestructure.database.AppDatabase
import com.example.infraestructure.database.RoomDataSource
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
    fun motorcycleDataSourceProvider(db: AppDatabase): MotorcycleRepository =
        RoomDataSource(db)

    @Provides
    fun carDataSourceProvider(db: AppDatabase): CarRepository =
        RoomDataSource(db)
}