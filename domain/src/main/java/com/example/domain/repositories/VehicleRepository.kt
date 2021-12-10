package com.example.domain.repositories


interface VehicleRepository<T> {
    suspend fun saveVehicle(data: T)
    suspend fun getVehicles(): List<T>
    suspend fun payParking(data: T)
}