package com.example.domainlibrary.repositories


import com.example.domainlibrary.entities.Car

interface LocalDataSource {
    suspend fun saveCar(car: Car)
    suspend fun getListCars(): List<Car>
    suspend fun payParkingCar(car: Car)
}