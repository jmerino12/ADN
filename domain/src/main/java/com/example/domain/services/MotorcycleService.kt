package com.example.domain.services

import com.example.domain.entities.Motorcycle
import com.example.domain.repositories.MotorcycleRepository

class MotorcycleService(val motorcycleRepository: MotorcycleRepository) {

    suspend fun saveMotorcycle(motorcycle: Motorcycle) {
        motorcycleRepository.saveMotorcycle(motorcycle)
    }

    suspend fun getMotorcycles(): List<Motorcycle> {
        return motorcycleRepository.getListMotorcycle()
    }

    suspend fun payParking(motorcycle: Motorcycle) {
        motorcycleRepository.payParkingMotorcycle(motorcycle)
    }
}