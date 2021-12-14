package com.example.domain.vehicle.entities

class Car(
    licencePlate: String,
    dateEnter: Long,
) :
    Vehicle(licencePlate, dateEnter) {
    companion object {
        private const val VALUE_DAY_CAR = 8000.0
        private const val VALUE_HOUR_CAR = 1000.0
    }


    override fun payParking(): Double {
        return calculatePaymentParking(VALUE_DAY_CAR, VALUE_HOUR_CAR)
    }

}