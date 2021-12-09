package com.example.adn.model.database


import com.example.domain.entities.Car
import com.example.domain.entities.Motorcycle
import com.example.domain.repositories.CarRepository
import com.example.domain.repositories.MotorcycleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RoomDataSource(db: AppDatabase) : MotorcycleRepository, CarRepository {

    private val vehicleDao = db.vehicleDao()

    override suspend fun saveCar(car: Car) = withContext(Dispatchers.IO) {
        vehicleDao.insertCar(car.toRoomVehicle())
    }

    override suspend fun getListCars(): List<Car> = withContext(Dispatchers.IO) {
        vehicleDao.getAllCars().map { it.toDomain() }
    }

    override suspend fun payParkingCar(car: Car) = withContext(Dispatchers.IO) {
        vehicleDao.deleteCar(car = car.toRoomVehicle())
    }

    override suspend fun saveMotorcycle(motorcycle: Motorcycle) {
        vehicleDao.insertMotorCycle(motorcycle.toRoomVehicle())
    }

    override suspend fun getListMotorcycle(): List<Motorcycle> = withContext(Dispatchers.IO) {
        vehicleDao.getAllMotorCycle().map { it.toDomain() }
    }

    override suspend fun payParkingMotorcycle(motorcycle: Motorcycle) =
        withContext(Dispatchers.IO) {
            vehicleDao.deleteMotorcycle(motorcycle.toRoomVehicle())
        }

}