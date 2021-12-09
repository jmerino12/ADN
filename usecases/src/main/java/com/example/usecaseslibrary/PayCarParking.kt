package com.example.usecaseslibrary

import com.example.datalibrary.repository.CarRepository
import com.example.domainlibrary.entities.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PayCarParking(
    private val carRepository: CarRepository,
) {
    suspend fun invoke(car: Car) = withContext(Dispatchers.IO) {
        carRepository.payParking(car)
    }
}