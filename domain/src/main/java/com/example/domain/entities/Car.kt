package com.example.domain.entities

class Car(
    licencePlate: String,
    cylinderCapacity: Double = 0.0,
    dateEnter: Long,
) :
    Vehicle(licencePlate, cylinderCapacity, dateEnter) {

    private val VALUE_DAY_CAR = 8000.0
    private val VALUE_HOUR_CAR = 1000.0

    override fun payParking(): Double {
        return calculatePaymentParking(VALUE_DAY_CAR, VALUE_HOUR_CAR)
    }

}