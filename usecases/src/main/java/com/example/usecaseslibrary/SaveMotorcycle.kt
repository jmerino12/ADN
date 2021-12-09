package com.example.usecaseslibrary


import com.example.data.repository.MotorcycleRepository
import com.example.domainlibrary.entities.Motorcycle

class SaveMotorcycle(private val motorcycleRepository: MotorcycleRepository) {
    suspend fun invoke(motorcycle: Motorcycle) = motorcycleRepository.saveMotorcycle(motorcycle)
}