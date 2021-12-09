package com.example.domainlibrary.repositories

import com.example.domainlibrary.entities.Car

interface CarRepository {
    suspend fun saveCar(car: Car)
    suspend fun getListCars(): List<Car>
    suspend fun payParkingCar(car: Car)
}