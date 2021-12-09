package com.example.adn.di

import com.example.domain.repositories.CarRepository
import com.example.domain.repositories.MotorcycleRepository
import com.example.domain.services.CarService
import com.example.domain.services.MotorcycleService
import dagger.Module
import dagger.Provides
import com.example.data.repository.CarRepository as CarRepositoryImp
import com.example.data.repository.MotorcycleRepository as MotorcycleImp


@Module
class DataModule {
    @Provides
    fun carRepositoryProvider(
        carRepository: CarRepository
    ) = CarRepositoryImp(carRepository)

    @Provides
    fun motorcycleRepositoryProvider(
        motorcycleRepository: MotorcycleRepository
    ) = MotorcycleImp(motorcycleRepository)

    @Provides
    fun carServiceProvider(carRepository: CarRepository) = CarService(carRepository)

    @Provides
    fun motorcycleServiceProvider(motorcycleRepository: MotorcycleRepository) =
        MotorcycleService(motorcycleRepository)

}