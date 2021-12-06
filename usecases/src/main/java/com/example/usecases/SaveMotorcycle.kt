package com.example.usecases


import com.example.data.repository.MotorcycleRepository
import com.example.domain.entities.Motorcycle

class SaveMotorcycle(private val motorcycleRepository: MotorcycleRepository) {
    suspend fun invoke(motorcycle: Motorcycle) = motorcycleRepository.saveMotorcycle(motorcycle)
}