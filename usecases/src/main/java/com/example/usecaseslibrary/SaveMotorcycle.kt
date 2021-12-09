package com.example.usecaseslibrary


import com.example.datalibrary.repository.MotorcycleRepository
import com.example.domainlibrary.entities.Motorcycle

class SaveMotorcycle(private val motorcycleRepository: MotorcycleRepository) {
    suspend fun invoke(motorcycle: Motorcycle) = motorcycleRepository.saveMotorcycle(motorcycle)
}