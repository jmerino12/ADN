package com.example.domain.vehicle.repositories

import com.example.domain.vehicle.entities.Vehicle

interface VehicleRepository<T> where T : Vehicle {
    suspend fun saveVehicle(data: T)
    suspend fun existVehicle(data: T): Boolean
    suspend fun countVehicleInParking(): Int
    suspend fun getVehicles(): List<T>
    suspend fun payParking(data: T)
}