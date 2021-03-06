package com.example.domain

import com.example.domain.vehicle.entities.Car
import com.example.domain.vehicle.entities.Motorcycle
import com.example.domain.vehicle.entities.Vehicle


class Parking {

    companion object {
        private const val LIMIT_CAR = 20
        private const val LIMIT_MOTORCYCLE = 10
    }

    fun isParkingFull(count: Int, vehicle: Vehicle): Boolean {
        return when (vehicle) {
            is Car -> {
               return count >= LIMIT_CAR
            }
            is Motorcycle -> {
                return count >= LIMIT_MOTORCYCLE
            }
            else -> false
        }

    }


}


