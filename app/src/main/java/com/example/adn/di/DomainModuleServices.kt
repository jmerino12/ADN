package com.example.adn.di

import com.example.domain.vehicle.entities.Car
import com.example.domain.vehicle.entities.Motorcycle
import com.example.domain.vehicle.repositories.VehicleRepository
import com.example.domain.vehicle.services.VehicleService
import dagger.Module
import dagger.Provides


@Module
class DomainModuleServices {
    @Provides
    fun carServiceProvider(vehicleRepository: VehicleRepository<Car>) =
        VehicleService(vehicleRepository)

    @Provides
    fun motorcycleServiceProvider(vehicleRepository: VehicleRepository<Motorcycle>) =
        VehicleService(vehicleRepository)

}