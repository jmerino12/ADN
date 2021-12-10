package com.example.domain.services

import com.example.domain.entities.Car
import com.example.domain.repositories.VehicleRepository


class CarService(private val carRepository: VehicleRepository<Car>) {

    suspend fun saveCar(car: Car) {
        return carRepository.saveVehicle(car)
    }

    suspend fun getCars(): List<Car> {
        return carRepository.getVehicles()
    }

    suspend fun payParking(car: Car) {
        return carRepository.payParking(car)
    }
}