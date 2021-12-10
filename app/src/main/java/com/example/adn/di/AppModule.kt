package com.example.adn.di

import android.app.Application
import androidx.room.Room
import com.example.domain.entities.Car
import com.example.domain.entities.Motorcycle
import com.example.domain.repositories.VehicleRepository
import com.example.infraestructure.database.AppDatabase
import com.example.infraestructure.database.RoomDataSourceCar
import com.example.infraestructure.database.RoomDataSourceMotorcycle
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
    fun motorcycleDataSourceProvider(db: AppDatabase): VehicleRepository<Motorcycle> =
        RoomDataSourceMotorcycle(db)

    @Provides
    fun carDataSourceProvider(db: AppDatabase): VehicleRepository<Car> =
        RoomDataSourceCar(db)
}