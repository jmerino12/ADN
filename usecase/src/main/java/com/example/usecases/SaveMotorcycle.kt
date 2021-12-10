package com.example.usecases


import com.example.domain.vehicle.entities.Motorcycle
import com.example.domain.vehicle.services.VehicleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveMotorcycle(private val vehicleService: VehicleService<Motorcycle>) {
    suspend fun invoke(motorcycle: Motorcycle) = withContext(Dispatchers.IO) {
        vehicleService.saveVehicle(motorcycle)
    }
}