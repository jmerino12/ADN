package com.example.data.repository


import com.example.domain.entities.Car
import com.example.domain.repositories.CarRepository

class CarRepository(
    private val carRepository: CarRepository
) {

    suspend fun getCars(): List<Car> {
        return carRepository.getListCars()
    }

    suspend fun saveCar(car: Car) = carRepository.saveCar(car)

    suspend fun payParking(car: Car) = carRepository.payParkingCar(car)
}