package com.example.adn.di

import android.app.Application
import androidx.room.Room
import com.example.domain.vehicle.entities.Car
import com.example.domain.vehicle.entities.Motorcycle
import com.example.domain.vehicle.repositories.VehicleRepository
import com.example.infraestructure.shared.AppDatabase
import com.example.infraestructure.vehicle.repository.RoomCarRepository
import com.example.infraestructure.vehicle.repository.RoomMotorcycleRepository
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
        RoomMotorcycleRepository(db)

    @Provides
    fun carDataSourceProvider(db: AppDatabase): VehicleRepository<Car> =
        RoomCarRepository(db)
}