package com.example.datalibrary.source


import com.example.domainlibrary.entities.Car
import com.example.domainlibrary.entities.Motorcycle

interface LocalDataSource {
    suspend fun saveCar(car: Car)
    suspend fun getListCars(): List<Car>
    suspend fun saveMotorcycle(motorcycle: Motorcycle)
    suspend fun getListMotorcycle(): List<Motorcycle>
    suspend fun payParkingCar(car: Car)
    suspend fun payParkingMotorcycle(motorcycle: Motorcycle)
}