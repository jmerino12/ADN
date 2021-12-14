package com.example.domain.vehicle.entities

import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDateTime
import java.time.Month
import java.time.OffsetDateTime

class MotorcycleTest {
    private val licensePLate = "BBB58C"

    @Test
    fun validateisCylinderCapacityMore500_ccLess500_false() {

        //ARRANGE
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 7, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        val motorcycle = Motorcycle(
            licencePlate = licensePLate,
            dateEnter = date,
            cylinderCapacity = 400.0
        )

        // ACT
        val result = motorcycle.cylinderCapacity <= 500

        //Assert
        assertTrue(result)
    }

    @Test
    fun validateisCylinderCapacityMore500_ccMore500_true() {

        //ARRANGE
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 7, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        val motorcycle = Motorcycle(
            licencePlate = licensePLate,
            dateEnter = date,
            cylinderCapacity = 550.0
        )

        // ACT
        val result = motorcycle.cylinderCapacity >= 500

        //ASSERT
        assertTrue(result)
    }

    @Test
    fun validateisCylinderCapacityMore500_cc500_true() {

        //ARRANGE
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 7, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        val motorcycle = Motorcycle(
            licencePlate = licensePLate,
            dateEnter = date,
            cylinderCapacity = 500.0
        )

        // ACT
        val result = motorcycle.cylinderCapacity >= 500

        //ASSERT
        assertTrue(result)
    }
}