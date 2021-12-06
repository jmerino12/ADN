package com.example.domain.entities


class Parking(
    private val carMax: Int = 20,
    private val motorcycleMax: Int = 10,
) {

    fun parkingCarIsFull(carSize: Int): Boolean {
        return carSize >= carMax
    }

    fun parkingMotorCycleIsFull(motorCycleSize: Int): Boolean {
        return motorCycleSize >= motorcycleMax
    }

}