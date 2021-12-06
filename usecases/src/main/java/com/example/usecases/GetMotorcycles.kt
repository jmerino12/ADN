package com.example.usecases

import com.example.data.repository.MotorcycleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMotorcycles(private val motorcycleRepository: MotorcycleRepository) {
    suspend fun invoke() = withContext(Dispatchers.IO) {
        motorcycleRepository.getMotorcycles()
    }
}