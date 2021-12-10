package com.example.domain.vehicle.services

import com.example.domain.vehicle.entities.Vehicle
import com.example.domain.vehicle.repositories.VehicleRepository

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