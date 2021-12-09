package com.example.adn.di

import com.example.domainlibrary.repositories.CarRepository
import com.example.domainlibrary.repositories.MotorcycleRepository
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
}