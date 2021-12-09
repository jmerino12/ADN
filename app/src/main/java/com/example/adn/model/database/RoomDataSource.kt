package com.example.adn.model.database


import com.example.adn.toDomain
import com.example.adn.toRoomVehicle
import com.example.datalibrary.source.LocalDataSource
import com.example.domainlibrary.entities.Car
import com.example.domainlibrary.entities.Motorcycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RoomDataSource(db: AppDatabase) : LocalDataSource {

    private val vehicleDao = db.vehicleDao()
    override suspend fun saveCar(car: Car) = withContext(Dispatchers.IO) {
        vehicleDao.insertCar(car.toRoomVehicle())
    }

    override suspend fun getListCars(): List<Car> = withContext(Dispatchers.IO) {
        vehicleDao.getAllCars().map { it.toDomain() }
    }

    override suspend fun saveMotorcycle(motorcycle: Motorcycle) {
        vehicleDao.insertMotorCycle(motorcycle.toRoomVehicle())
    }

    override suspend fun getListMotorcycle(): List<Motorcycle> = withContext(Dispatchers.IO) {
        vehicleDao.getAllMotorCycle().map { it.toDomain() }
    }

    override suspend fun payParkingCar(car: Car) = withContext(Dispatchers.IO) {
        vehicleDao.deleteCar(car = car.toRoomVehicle())
    }

    override suspend fun payParkingMotorcycle(motorcycle: Motorcycle) =
        withContext(Dispatchers.IO) {
            vehicleDao.deleteMotorcycle(motorcycle.toRoomVehicle())
        }


}