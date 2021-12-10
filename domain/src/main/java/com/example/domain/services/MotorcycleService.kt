package com.example.domain.services

import com.example.domain.entities.Motorcycle
import com.example.domain.repositories.VehicleRepository

class MotorcycleService(private val motorcycleRepository: VehicleRepository<Motorcycle>) {

    suspend fun saveMotorcycle(motorcycle: Motorcycle) {
        motorcycleRepository.saveVehicle(motorcycle)
    }

    suspend fun getMotorcycles(): List<Motorcycle> {
        return motorcycleRepository.getVehicles()
    }

    suspend fun payParking(motorcycle: Motorcycle) {
        motorcycleRepository.payParking(motorcycle)
    }
}