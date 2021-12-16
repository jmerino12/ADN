package com.example.infraestructure.vehicle.repository


import com.example.domain.vehicle.entities.Car
import com.example.domain.vehicle.repositories.VehicleRepository
import com.example.infraestructure.shared.AppDatabase
import com.example.infraestructure.vehicle.anticorruption.toDomain
import com.example.infraestructure.vehicle.anticorruption.toRoomVehicle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RoomCarRepository(db: AppDatabase) : VehicleRepository<Car> {

    private val vehicleDao = db.vehicleDao()

    override suspend fun saveVehicle(data: Car) = withContext(Dispatchers.IO) {
        vehicleDao.insertCar(data.toRoomVehicle())
    }

    override fun getVehicles(): Flow<List<Car>> {
        return vehicleDao.getAllCars().map { it.map { item -> item.toDomain() } }
    }

    override suspend fun payParking(data: Car) = withContext(Dispatchers.IO) {
        vehicleDao.deleteCar(car = data.toRoomVehicle())
    }

    override suspend fun existVehicle(data: Car): Boolean {
        return vehicleDao.existCar(data.licencePlate)
    }

    override suspend fun countVehicleInParking(): Int {
        return vehicleDao.totalOfCarsInParking()
    }

}