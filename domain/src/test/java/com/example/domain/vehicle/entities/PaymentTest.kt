package com.example.domain.vehicle.entities

import org.junit.Assert
import org.junit.Test

private const val DELTA: Double = 1e-15

class PaymentTest {
    private val date = System.currentTimeMillis()

    @Test
    fun parkingPayment_motorcycleCylinderUnder500ForHours() {

        //ARRANGE
        val bike = Motorcycle(
            dateEnter = date,
            licencePlate = "BBB57G",
            cylinderCapacity = 499.0,
        )

        //ACT
        val result = bike.payParking()

        //ASSERT
        Assert.assertEquals(500.0, result, DELTA)
    }

    @Test
    fun parkingPayment_motorcycleCylinder500ForHours() {

        //ARRANGE
        val bike = Motorcycle(
            dateEnter = date,
            licencePlate = "BBB57G",
            cylinderCapacity = 500.0,
        )

        //ACT
        val result = bike.payParking()

        //ASSERT
        Assert.assertEquals(result, 2500.0, DELTA)
    }

    @Test
    fun parkingPayment_carPerHours() {

        //ARRANGE
        val car = Car(
            dateEnter = date,
            licencePlate = "BBB57G",
        )
        //ACT
        val result = car.payParking()

        //ASSERT
        Assert.assertEquals(result, 1000.0, DELTA)
    }
}