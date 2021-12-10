package com.example.adn.di


import com.example.domain.entities.Car
import com.example.domain.entities.Motorcycle
import com.example.domain.services.VehicleService
import com.example.usecases.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getCarProvider(vehicleService: VehicleService<Car>) = GetCars(vehicleService)

    @Provides
    fun saveCarProvider(vehicleService: VehicleService<Car>) = SaveCar(vehicleService)

    @Provides
    fun payCarParking(vehicleService: VehicleService<Car>) =
        PayCarParking(vehicleService)

    @Provides
    fun getMotorcycleProvider(vehicleService: VehicleService<Motorcycle>) =
        GetMotorcycles(vehicleService)

    @Provides
    fun saveMotorcycleProvider(vehicleService: VehicleService<Motorcycle>) =
        SaveMotorcycle(vehicleService)


    @Provides
    fun payMotorcycleParking(vehicleService: VehicleService<Motorcycle>) =
        PayMotorcycleParking(vehicleService)
}