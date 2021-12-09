package com.example.domainlibrary.entities

class Motorcycle(
    licencePlate: String,
    cylinderCapacity: Double,
    dateEnter: Long,
) :
    Vehicle(licencePlate, cylinderCapacity, dateEnter) {

    private val VALUE_HOUR_MOTORCYCLE = 500.0
    private val VALUE_DAY_MOTORCYCLE = 4000.0

    override fun payParking(): Double {
        val totalToPay = calculatePaymentParking(
            VALUE_DAY_MOTORCYCLE,
            VALUE_HOUR_MOTORCYCLE
        )
        return if (isCylinderCapacityMore500()) {
            totalToPay + 2000
        } else {
            totalToPay
        }
    }


}



