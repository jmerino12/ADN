package com.example.adn.di

import com.example.data.repository.CarRepository
import com.example.data.repository.MotorcycleRepository
import com.example.data.source.LocalDataSource
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