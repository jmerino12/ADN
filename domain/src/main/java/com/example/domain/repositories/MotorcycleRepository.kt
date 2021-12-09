package com.example.domain.repositories

import com.example.domain.entities.Motorcycle

interface MotorcycleRepository {
    suspend fun saveMotorcycle(motorcycle: Motorcycle)
    suspend fun getListMotorcycle(): List<Motorcycle>
    suspend fun payParkingMotorcycle(motorcycle: Motorcycle)
}