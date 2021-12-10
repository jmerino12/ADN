package com.example.infraestructure.database


import com.example.domain.entities.Car
import com.example.domain.repositories.VehicleRepository
import com.example.infraestructure.mapper.toDomain
import com.example.infraestructure.mapper.toRoomVehicle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSourceCar(db: AppDatabase) : VehicleRepository<Car> {

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