package com.example.data.source

import com.example.domain.entities.Car
import com.example.domain.entities.Motorcycle

interface LocalDataSource {
    suspend fun saveCar(car: Car)
    suspend fun getListCars(): List<Car>
    suspend fun saveMotorcycle(motorcycle: Motorcycle)
    suspend fun getListMotorcycle(): List<Motorcycle>
}