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
        var totalToPay = calculatePaymentParking(
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



