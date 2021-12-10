package com.example.domain.services

import com.example.domain.entities.Vehicle
import com.example.domain.repositories.VehicleRepository

class VehicleService<T>(private val repository: VehicleRepository<T>) where T : Vehicle {

    suspend fun saveVehicle(vehicle: T) {
        repository.saveVehicle(vehicle)
    }

    suspend fun getVehicles(): List<T> {
        return repository.getVehicles()
    }

    suspend fun payParking(vehicle: T) {
        repository.payParking(vehicle)
    }
}