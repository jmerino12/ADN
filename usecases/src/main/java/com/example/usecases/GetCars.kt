package com.example.usecases

import com.example.data.repository.CarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCars(private val carRepository: CarRepository) {
    suspend fun invoke() = withContext(Dispatchers.IO) {
        carRepository.getCars()
    }
}