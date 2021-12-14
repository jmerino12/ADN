package com.example.infraestructure.vehicle.repository

import com.example.domain.vehicle.entities.Motorcycle
import com.example.domain.vehicle.repositories.VehicleRepository
import com.example.infraestructure.shared.AppDatabase
import com.example.infraestructure.vehicle.anticorruption.toDomain
import com.example.infraestructure.vehicle.anticorruption.toRoomVehicle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomMotorcycleRepository(db: AppDatabase) : VehicleRepository<Motorcycle> {

    private val vehicleDao = db.vehicleDao()

    override suspend fun saveVehicle(data: Motorcycle) = withContext(Dispatchers.IO) {
        vehicleDao.insertMotorCycle(data.toRoomVehicle())
    }

    override suspend fun getVehicles(): List<Motorcycle> = withContext(Dispatchers.IO) {
        vehicleDao.getAllMotorCycle().map { it.toDomain() }
    }

    override suspend fun payParking(data: Motorcycle) =
        withContext(Dispatchers.IO) {
            vehicleDao.deleteMotorcycle(data.toRoomVehicle())
        }

    override suspend fun existVehicle(data: Motorcycle): Boolean {
        return vehicleDao.existMotorcycle(data.licencePlate)
    }

    override suspend fun countVehicleInParking(): Int {
        return vehicleDao.totalOfMotorcycleInParking()
    }

}