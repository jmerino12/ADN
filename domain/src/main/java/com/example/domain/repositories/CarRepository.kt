package com.example.domain.repositories

import com.example.domain.entities.Car

interface CarRepository {
    suspend fun saveCar(car: Car)
    suspend fun getListCars(): List<Car>
    suspend fun payParkingCar(car: Car)
}