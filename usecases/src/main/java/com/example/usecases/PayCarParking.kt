package com.example.usecases

import com.example.domain.entities.Car
import com.example.domain.services.CarService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PayCarParking(
    private val carService: CarService
) {
    suspend fun invoke(car: Car) = withContext(Dispatchers.IO) {
        carService.payParking(car)
    }
}