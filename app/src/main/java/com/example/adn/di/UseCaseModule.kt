package com.example.adn.di

import com.example.data.repository.CarRepository
import com.example.data.repository.MotorcycleRepository
import com.example.usecases.GetCars
import com.example.usecases.GetMotorcycles
import com.example.usecases.SaveCar
import com.example.usecases.SaveMotorcycle
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
}