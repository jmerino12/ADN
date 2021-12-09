package com.example.adn.di


import com.example.datalibrary.repository.CarRepository
import com.example.datalibrary.repository.MotorcycleRepository
import com.example.usecaseslibrary.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getCarProvider(carRepository: CarRepository) = GetCars(carRepository)

    @Provides
    fun saveCarProvider(carRepository: CarRepository) = SaveCar(carRepository)

    @Provides
    fun getMotorcycleProvider(motorcycleRepository: MotorcycleRepository) =
        GetMotorcycles(motorcycleRepository)

    @Provides
    fun saveMotorcycleProvider(motorcycleRepository: MotorcycleRepository) =
        SaveMotorcycle(motorcycleRepository)

    @Provides
    fun payCarParking(carRepository: CarRepository) =
        PayCarParking(carRepository)

    @Provides
    fun payMotorcycleParking(motorcycleRepository: MotorcycleRepository) =
        PayMotorcycleParking(motorcycleRepository)
}