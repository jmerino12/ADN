package com.example.domain.vehicle.entities

class Motorcycle(
    licencePlate: String,
    dateEnter: Long,
    val cylinderCapacity: Double,
) :
    Vehicle(licencePlate = licencePlate, dateEnter = dateEnter) {

    private val VALUE_HOUR_MOTORCYCLE = 500.0
    private val VALUE_DAY_MOTORCYCLE = 4000.0
    private val CYLINDER_CAPACITY_500 = 500

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

    private fun isCylinderCapacityMore500(): Boolean {
        return cylinderCapacity >= CYLINDER_CAPACITY_500
    }

}



