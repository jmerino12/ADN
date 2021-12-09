package com.example.usecaseslibrary

import com.example.domain.entities.Car
import com.example.domain.services.CarService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveCar(private val carService: CarService) {
    suspend fun invoke(car: Car) = withContext(Dispatchers.IO) {
        carService.saveCar(car)
    }
}