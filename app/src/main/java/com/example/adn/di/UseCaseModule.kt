package com.example.adn.di


import com.example.datalibrary.repository.CarRepository
import com.example.datalibrary.repository.MotorcycleRepository
import com.example.usecaseslibrary.GetCars
import com.example.usecaseslibrary.GetMotorcycles
import com.example.usecaseslibrary.SaveCar
import com.example.usecaseslibrary.SaveMotorcycle
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