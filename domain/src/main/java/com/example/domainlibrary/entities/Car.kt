package com.example.domainlibrary.entities

import com.example.domainlibrary.util.PriceValues.VALUE_DAY_CAR
import com.example.domainlibrary.util.PriceValues.VALUE_HOUR_CAR


class Car(
    licencePlate: String,
    cylinderCapacity: Double = 0.0,
    dateEnter: Long,
) :
    Vehicle(licencePlate, cylinderCapacity, dateEnter) {

    override fun payParking(): Double {
        return calculatePaymentParking(VALUE_DAY_CAR, VALUE_HOUR_CAR)
    }

}