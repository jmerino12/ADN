package com.example.adn.di

import com.example.datalibrary.repository.CarRepository
import com.example.datalibrary.repository.MotorcycleRepository
import com.example.domainlibrary.repositories.LocalDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun carRepositoryProvider(
        localDataSource: LocalDataSource
    ) = CarRepository(localDataSource = localDataSource)

    @Provides
    fun motorcycleRepositoryProvider(
        localDataSource: LocalDataSource
    ) = MotorcycleRepository(localDataSource = localDataSource)
}