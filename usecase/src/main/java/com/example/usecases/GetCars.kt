package com.example.usecases

import com.example.domain.vehicle.entities.Car
import com.example.domain.vehicle.services.VehicleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCars(private val vehicleService: VehicleService<Car>) {
    suspend fun invoke() = withContext(Dispatchers.IO) {
        vehicleService.getVehicles()
    }
}