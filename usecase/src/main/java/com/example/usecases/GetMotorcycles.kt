package com.example.usecases

import com.example.domain.vehicle.entities.Motorcycle
import com.example.domain.vehicle.services.VehicleService
import kotlinx.coroutines.flow.Flow

class GetMotorcycles(private val vehicleService: VehicleService<Motorcycle>) {
    fun invoke(): Flow<List<Motorcycle>> {
        return vehicleService.getVehicles()
    }
}