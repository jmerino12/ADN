package com.example.domain.entities

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime
import java.time.Month
import java.time.OffsetDateTime


private const val DELTA: Double = 1e-15

class PaymentTest {

    @Test
    fun parkingPayment_motorcycleCylinderUnder500Per27Hours() {
        //Arrange
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 7, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        val hour27 = 97200000
        val bike = Motorcycle(
            dateEnter = date,
            licencePlate = "BBB57G",
            cylinderCapacity = 499.0,
            dateExit = date + hour27
        )
        //Act
        val result = bike.payParking()
        //Assert
        assertEquals(result, 5500.0, DELTA)
    }

    @Test
    fun parkingPayment_motorcycleCylinder500Per27Hours() {
        //Arrange
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 7, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        val hour27 = 97200000
        val bike = Motorcycle(
            dateEnter = date,
            licencePlate = "BBB57G",
            cylinderCapacity = 500.0,
            dateExit = date + hour27
        )
        //Act
        val result = bike.payParking()
        //Assert
        assertEquals(result, 7500.0, DELTA)
    }

    @Test
    fun parkingPayment_carPer27Hours() {
        //Arrange
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 7, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        val hour27 = 97200000
        val car = Car(
            dateEnter = date,
            dateExit = date + hour27,
            licencePlate = "BBB57G",
        )
        //Act
        val result = car.payParking()
        //Assert
        assertEquals(result, 11000.0, DELTA)
    }
}