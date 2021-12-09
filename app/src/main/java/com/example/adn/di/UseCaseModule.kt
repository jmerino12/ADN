package com.example.adn.di


import com.example.domain.services.MotorcycleService
import com.example.usecases.GetMotorcycles
import com.example.usecases.PayMotorcycleParking
import com.example.usecases.SaveMotorcycle
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    /*@Provides
    fun getCarProvider(carService: CarService) = GetCars(carService)

    @Provides
    fun saveCarProvider(carService: CarService) = SaveCar(carService)

    @Provides
    fun payCarParking(carService: CarService) =
        PayCarParking(carService)*/

    @Provides
    fun getMotorcycleProvider(motorcycleService: MotorcycleService) =
        GetMotorcycles(motorcycleService)

    @Provides
    fun saveMotorcycleProvider(motorcycleService: MotorcycleService) =
        SaveMotorcycle(motorcycleService)


    @Provides
    fun payMotorcycleParking(motorcycleService: MotorcycleService) =
        PayMotorcycleParking(motorcycleService)
}