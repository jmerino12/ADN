package com.example.domain.entities

import com.example.domain.util.InvalidDataException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.time.LocalDateTime
import java.time.Month
import java.time.OffsetDateTime
import java.util.*


class CarTest {

    @Test
    fun validateLicensePlateCamelCase_licensePlateMathWithRegex_trueCreateObject() {
        //ARRANGE
        val licensePLate = "bzy44g"
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 7, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        // ACT
        val car = Car(licencePlate = licensePLate, dateEnter = date)
        assertNotNull(car)
    }

    @Test
    fun validateLicense_with7Characters_exceptionError() {
        //ARRANGE
        val licensePLate = "bzy44gg"
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 7, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        // ACT
        try {
            val car = Car(licencePlate = licensePLate, dateEnter = date)
        } catch (e: InvalidDataException) {
            //Assert
            assertEquals("No tiene el estandar de la placa", e.message)
        }
    }

    @Test
    fun validateInitialLetterA_CarDifferentToSundayAndMonday_noCreateObject() {
        //ARRANGE
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 7, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        // ACT
        try {
            val car = Car(licencePlate = "AAA572", dateEnter = date)
        } catch (e: InvalidDataException) {
            //ASSERT
            assertEquals("No tiene permitido el ingreso", e.message)
        }
    }

    @Test
    fun validateInitialLetterA_CarSunday_createObject() {
        //ARRANGE
        // val offSetDateTime = OffsetDateTime.now()
        val date = Date(2020, 12, 5) /*LocalDateTime.of(2021, Month.DECEMBER, 5, 5, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()*/
        //ACT
        val car = Car(licencePlate = "AAA572", dateEnter = date.time)
        //ASSERT
        assertNotNull(car)
    }
}