package com.example.usecaseslibrary

import com.example.data.repository.CarRepository
import com.example.domainlibrary.entities.Car

class SaveCar(private val carRepository: CarRepository) {
    suspend fun invoke(car: Car) = carRepository.saveCar(car)
}