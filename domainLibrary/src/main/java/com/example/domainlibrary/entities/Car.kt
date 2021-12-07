package com.example.domainlibrary.entities

import com.example.domainlibrary.util.PriceValues.VALUE_DAY_CAR
import com.example.domainlibrary.util.PriceValues.VALUE_HOUR_CAR


class Car(
    licencePlate: String,
    cylinderCapacity: Double = 0.0,
    dateEnter: Long,
    dateExit: Long = 0
) :
    Vehicle(licencePlate, cylinderCapacity, dateEnter, dateExit) {

    override fun payParking(): Double {
        return calculatePaymentParking(VALUE_DAY_CAR, VALUE_HOUR_CAR)
    }

}