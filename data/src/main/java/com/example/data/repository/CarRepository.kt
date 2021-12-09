package com.example.datalibrary.repository


import com.example.domainlibrary.entities.Car
import com.example.domainlibrary.repositories.LocalDataSource

class CarRepository(
    private val localDataSource: LocalDataSource
) {

    suspend fun getCars(): List<Car> {
        return localDataSource.getListCars()
    }

    suspend fun saveCar(car: Car) = localDataSource.saveCar(car)

    suspend fun payParking(car: Car) = localDataSource.payParkingCar(car)
}