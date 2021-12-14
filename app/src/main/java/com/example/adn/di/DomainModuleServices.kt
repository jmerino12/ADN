package com.example.adn.di

import com.example.domain.Parking
import com.example.domain.vehicle.entities.Car
import com.example.domain.vehicle.entities.Motorcycle
import com.example.domain.vehicle.repositories.VehicleRepository
import com.example.domain.vehicle.services.VehicleService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DomainModuleServices {

    @Provides
    @Singleton
    fun parkingProvider() = Parking()

    @Provides
    fun carServiceProvider(vehicleRepository: VehicleRepository<Car>, parking: Parking) =
        VehicleService(vehicleRepository, parking)

    @Provides
    fun motorcycleServiceProvider(
        vehicleRepository: VehicleRepository<Motorcycle>,
        parking: Parking
    ) =
        VehicleService(vehicleRepository, parking)

}