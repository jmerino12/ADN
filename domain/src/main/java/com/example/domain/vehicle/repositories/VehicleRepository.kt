package com.example.domain.vehicle.repositories

import com.example.domain.vehicle.entities.Vehicle
import kotlinx.coroutines.flow.Flow

interface VehicleRepository<T> where T : Vehicle {
    suspend fun saveVehicle(data: T)
    suspend fun existVehicle(data: T): Boolean
    suspend fun countVehicleInParking(): Int
    fun getVehicles(): Flow<List<T>>
    suspend fun payParking(data: T)
}