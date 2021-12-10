package com.example.adn.di

import com.example.domain.entities.Car
import com.example.domain.entities.Motorcycle
import com.example.domain.repositories.VehicleRepository
import com.example.domain.services.CarService
import com.example.domain.services.MotorcycleService
import dagger.Module
import dagger.Provides


@Module
class DomainModuleServices {
    @Provides
    fun carServiceProvider(vehicleRepository: VehicleRepository<Car>) =
        CarService(vehicleRepository)

    @Provides
    fun motorcycleServiceProvider(vehicleRepository: VehicleRepository<Motorcycle>) =
        MotorcycleService(vehicleRepository)

}