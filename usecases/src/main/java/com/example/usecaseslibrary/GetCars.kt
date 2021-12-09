package com.example.usecaseslibrary

import com.example.datalibrary.repository.CarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCars(private val carRepository: CarRepository) {
    suspend fun invoke() = withContext(Dispatchers.IO) {
        carRepository.getCars()
    }
}