package com.example.usecases

import com.example.data.repository.CarRepository
import com.example.domain.entities.Car

class SaveCar(private val carRepository: CarRepository) {
    suspend fun invoke(car: Car) = carRepository.saveCar(car)
}