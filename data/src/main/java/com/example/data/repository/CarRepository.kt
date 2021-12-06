package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.domain.entities.Car

class CarRepository(
    private val localDataSource: LocalDataSource
) {

    suspend fun getCars(): List<Car> {
        return localDataSource.getListCars()
    }

    suspend fun saveCar(car: Car) = localDataSource.saveCar(car)
}