package com.example.infraestructure.database.repository


import com.example.domain.entities.Car
import com.example.domain.repositories.CarRepository

class CarRepository(
    private val carRepository: CarRepository
) : CarRepository {

    override suspend fun saveCar(car: Car) = carRepository.saveCar(car)
    override suspend fun getListCars(): List<Car> = carRepository.getListCars()
    override suspend fun payParkingCar(car: Car) = carRepository.payParkingCar(car)

}