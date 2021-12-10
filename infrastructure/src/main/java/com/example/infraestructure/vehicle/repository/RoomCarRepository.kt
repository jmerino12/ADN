package com.example.infraestructure.vehicle.repository


import com.example.domain.vehicle.entities.Car
import com.example.domain.vehicle.repositories.VehicleRepository
import com.example.infraestructure.shared.AppDatabase
import com.example.infraestructure.vehicle.anticorruption.toDomain
import com.example.infraestructure.vehicle.anticorruption.toRoomVehicle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomCarRepository(db: AppDatabase) : VehicleRepository<Car> {

    private val vehicleDao = db.vehicleDao()

    override suspend fun saveVehicle(data: Car) = withContext(Dispatchers.IO) {
        vehicleDao.insertCar(data.toRoomVehicle())
    }

    override suspend fun getVehicles(): List<Car> = withContext(Dispatchers.IO) {
        vehicleDao.getAllCars().map { it.toDomain() }
    }

    override suspend fun payParking(data: Car) = withContext(Dispatchers.IO) {
        vehicleDao.deleteCar(car = data.toRoomVehicle())
    }

}