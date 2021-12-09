package com.example.usecaseslibrary

import com.example.data.repository.MotorcycleRepository
import com.example.domainlibrary.entities.Motorcycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PayMotorcycleParking(
    private val motorcycleRepository: MotorcycleRepository
) {
    suspend fun invoke(motorcycle: Motorcycle) = withContext(Dispatchers.IO) {
        motorcycleRepository.payParking(motorcycle)
    }
}