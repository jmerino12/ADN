package com.example.data.repository

import com.example.domainlibrary.entities.Motorcycle
import com.example.domainlibrary.repositories.MotorcycleRepository

class MotorcycleRepository(
    private val motorcycleRepository: MotorcycleRepository
) {
    suspend fun getMotorcycles(): List<Motorcycle> {
        return motorcycleRepository.getListMotorcycle()
    }

    suspend fun saveMotorcycle(motorcycle: Motorcycle) =
        motorcycleRepository.saveMotorcycle(motorcycle)

    suspend fun payParking(motorcycle: Motorcycle) =
        motorcycleRepository.payParkingMotorcycle(motorcycle)

}