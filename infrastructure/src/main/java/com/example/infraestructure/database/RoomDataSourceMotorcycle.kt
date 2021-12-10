package com.example.infraestructure.database

import com.example.domain.entities.Motorcycle
import com.example.domain.repositories.VehicleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSourceMotorcycle(db: AppDatabase) : VehicleRepository<Motorcycle> {

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

}