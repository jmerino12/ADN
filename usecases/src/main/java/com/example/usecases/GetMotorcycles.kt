package com.example.usecases

import com.example.domain.services.MotorcycleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMotorcycles(private val motorcycleService: MotorcycleService) {
    suspend fun invoke() = withContext(Dispatchers.IO) {
        motorcycleService.getMotorcycles()
    }
}