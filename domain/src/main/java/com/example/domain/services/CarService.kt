package com.example.domain.services

import com.example.domain.entities.Car
import com.example.domain.repositories.CarRepository


class CarService(private val carRepository: CarRepository) {

    suspend fun saveCar(car: Car) {
        return carRepository.saveCar(car)
    }

    suspend fun getCars(): List<Car> {
        return carRepository.getListCars()
    }

    suspend fun payParking(car: Car) {
        return carRepository.payParkingCar(car)
    }
}