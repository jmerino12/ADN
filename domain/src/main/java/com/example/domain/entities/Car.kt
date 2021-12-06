package com.example.domain.entities

import com.example.domain.util.PriceValues.VALUE_DAY_CAR
import com.example.domain.util.PriceValues.VALUE_HOUR_CAR


class Car(
    licencePlate: String,
    cylinderCapacity: Double = 0.0,
    dateEnter: Long,
    dateExit: Long = dateEnter
) :
    Vehicle(licencePlate, cylinderCapacity, dateEnter, dateExit) {

    override fun payParking(): Double {
        val days = calculatePaymentParking().split(",")[0].toDouble()
        val hours = calculatePaymentParking().split(",")[1].toDouble()
        return (VALUE_DAY_CAR * days) + (hours * VALUE_HOUR_CAR)
    }

}