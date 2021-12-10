package com.example.usecases

import com.example.domain.entities.Motorcycle
import com.example.domain.services.VehicleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMotorcycles(private val vehicleService: VehicleService<Motorcycle>) {
    suspend fun invoke() = withContext(Dispatchers.IO) {
        vehicleService.getVehicles()
    }
}