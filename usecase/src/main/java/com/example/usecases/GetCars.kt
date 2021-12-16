package com.example.usecases

import com.example.domain.vehicle.entities.Car
import com.example.domain.vehicle.services.VehicleService
import kotlinx.coroutines.flow.Flow

class GetCars(private val vehicleService: VehicleService<Car>) {
    fun invoke(): Flow<List<Car>> {
        return vehicleService.getVehicles()
    }
}