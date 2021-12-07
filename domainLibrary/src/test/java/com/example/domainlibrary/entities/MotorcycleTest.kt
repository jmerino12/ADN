package com.example.domainlibrary.entities

import org.junit.Assert.assertFalse
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
            dateExit = 0,
            cylinderCapacity = 400.0
        )
        // ACT
        val result = motorcycle.isCylinderCapacityMore500()
        //Assert
        assertFalse(result)
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
            dateExit = 0,
            cylinderCapacity = 550.0
        )

        // ACT
        val result = motorcycle.isCylinderCapacityMore500()
        //Assert
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
            dateExit = 0,
            cylinderCapacity = 500.0
        )

        // ACT
        val result = motorcycle.isCylinderCapacityMore500()
        //Assert
        assertTrue(result)
    }
}