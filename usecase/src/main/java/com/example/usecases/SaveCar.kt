package com.example.usecases

import com.example.domain.entities.Car
import com.example.domain.services.VehicleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveCar(private val vehicleService: VehicleService<Car>) {
    suspend fun invoke(car: Car) = withContext(Dispatchers.IO) {
        vehicleService.saveVehicle(car)
    }
}