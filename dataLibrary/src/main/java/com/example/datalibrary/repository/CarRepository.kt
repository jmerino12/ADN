package com.example.datalibrary.repository


import com.example.datalibrary.source.LocalDataSource
import com.example.domainlibrary.entities.Car

class CarRepository(
    private val localDataSource: LocalDataSource
) {

    suspend fun getCars(): List<Car> {
        return localDataSource.getListCars()
    }

    suspend fun saveCar(car: Car) = localDataSource.saveCar(car)
}