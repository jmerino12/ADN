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
        val days = calculatePaymentParking().split(",")[0].toDouble()
        val hours = calculatePaymentParking().split(",")[1].toDouble()
        return (VALUE_DAY_CAR * days) + (hours * VALUE_HOUR_CAR)
    }

}