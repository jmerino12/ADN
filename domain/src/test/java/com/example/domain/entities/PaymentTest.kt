package com.example.domain.entities

import org.junit.Assert
import org.junit.Test

private const val DELTA: Double = 1e-15

class PaymentTest {
    private val date = System.currentTimeMillis()

    @Test
    fun parkingPayment_motorcycleCylinderUnder500ForHours() {
        //Arrange
        val bike = Motorcycle(
            dateEnter = date,
            licencePlate = "BBB57G",
            cylinderCapacity = 499.0,
        )
        //Act
        val result = bike.payParking()
        //Assert
        Assert.assertEquals(500.0, result, DELTA)
    }

    @Test
    fun parkingPayment_motorcycleCylinder500ForHours() {
        //Arrange
        val bike = Motorcycle(
            dateEnter = date,
            licencePlate = "BBB57G",
            cylinderCapacity = 500.0,
        )
        //Act
        val result = bike.payParking()
        //Assert
        Assert.assertEquals(result, 2500.0, DELTA)
    }

    @Test
    fun parkingPayment_carPerHours() {
        //Arrange
        val car = Car(
            dateEnter = date,
            licencePlate = "BBB57G",
        )
        //Act
        val result = car.payParking()
        //Assert
        Assert.assertEquals(result, 1000.0, DELTA)
    }
}