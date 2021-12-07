package com.example.domainlibrary.entities

import com.example.domainlibrary.util.PriceValues


class Motorcycle(
    licencePlate: String,
    cylinderCapacity: Double,
    dateEnter: Long,
    dateExit: Long = 0
) :
    Vehicle(licencePlate, cylinderCapacity, dateEnter, dateExit) {


    override fun payParking(): Double {
        val days = calculatePaymentParking().split(",")[0].toDouble()
        val hours = calculatePaymentParking().split(",")[1].toDouble()
        return if (isCylinderCapacityMore500()) {
            (PriceValues.VALUE_DAY_MOTORCYCLE * days) + (hours * PriceValues.VALUE_HOUR_MOTORCYCLE) + 2000
        } else {
            (PriceValues.VALUE_DAY_MOTORCYCLE * days) + (hours * PriceValues.VALUE_HOUR_MOTORCYCLE)
        }
    }


}



