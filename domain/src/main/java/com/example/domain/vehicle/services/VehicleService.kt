package com.example.domain.vehicle.services

import com.example.domain.Parking
import com.example.domain.shared.exceptions.LimitParkingException
import com.example.domain.shared.exceptions.VehicleExistsException
import com.example.domain.vehicle.entities.Vehicle
import com.example.domain.vehicle.repositories.VehicleRepository
import kotlinx.coroutines.flow.Flow

class VehicleService<T>(
    private val repository: VehicleRepository<T>,
    private val parking: Parking
) where T : Vehicle {

    suspend fun saveVehicle(vehicle: T) {
        if (!parking.isParkingFull(repository.countVehicleInParking(), vehicle)) {
            if (!repository.existVehicle(vehicle)) {
                repository.saveVehicle(vehicle)
            } else {
                throw VehicleExistsException()
            }
        } else {
            throw LimitParkingException()
        }
    }

    suspend fun getVehicles(): Flow<List<T>> {
        return repository.getVehicles()
    }

    suspend fun payParking(vehicle: T) {
        return repository.payParking(vehicle)
    }
}