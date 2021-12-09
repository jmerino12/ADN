package com.example.domainlibrary.repositories

import com.example.domainlibrary.entities.Motorcycle

interface MotorcycleRepository {
    suspend fun saveMotorcycle(motorcycle: Motorcycle)
    suspend fun getListMotorcycle(): List<Motorcycle>
    suspend fun payParkingMotorcycle(motorcycle: Motorcycle)
}