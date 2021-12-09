package com.example.usecases

import com.example.domain.entities.Motorcycle
import com.example.domain.services.MotorcycleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PayMotorcycleParking(
    private val motorcycleService: MotorcycleService
) {
    suspend fun invoke(motorcycle: Motorcycle) = withContext(Dispatchers.IO) {
        motorcycleService.payParking(motorcycle)
    }
}