package com.example.domain.repositories

import com.example.domain.entities.Vehicle

interface VehicleRepository<T> where T : Vehicle {
    suspend fun saveVehicle(data: T)
    suspend fun getVehicles(): List<T>
    suspend fun payParking(data: T)
}