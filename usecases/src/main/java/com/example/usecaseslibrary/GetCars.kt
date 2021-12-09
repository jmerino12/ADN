package com.example.usecaseslibrary

import com.example.domain.services.CarService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCars(private val carService: CarService) {
    suspend fun invoke() = withContext(Dispatchers.IO) {
        carService.getCars()
    }
}