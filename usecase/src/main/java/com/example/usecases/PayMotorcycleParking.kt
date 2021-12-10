package com.example.usecases

import com.example.domain.entities.Motorcycle
import com.example.domain.services.VehicleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PayMotorcycleParking(
    private val vehicleService: VehicleService<Motorcycle>
) {
    suspend fun invoke(motorcycle: Motorcycle) = withContext(Dispatchers.IO) {
        vehicleService.payParking(motorcycle)
    }
}