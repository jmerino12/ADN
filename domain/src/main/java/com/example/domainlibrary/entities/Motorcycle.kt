package com.example.domainlibrary.entities

import com.example.domainlibrary.util.PriceValues


class Motorcycle(
    licencePlate: String,
    cylinderCapacity: Double,
    dateEnter: Long,
) :
    Vehicle(licencePlate, cylinderCapacity, dateEnter) {


    override fun payParking(): Double {
        val totalToPay = calculatePaymentParking(
            PriceValues.VALUE_DAY_MOTORCYCLE,
            PriceValues.VALUE_HOUR_MOTORCYCLE
        )
        return if (isCylinderCapacityMore500()) {
            totalToPay + 2000
        } else {
            totalToPay
        }
    }


}



