package com.example.usecaseslibrary

import com.example.datalibrary.repository.MotorcycleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMotorcycles(private val motorcycleRepository: MotorcycleRepository) {
    suspend fun invoke() = withContext(Dispatchers.IO) {
        motorcycleRepository.getMotorcycles()
    }
}