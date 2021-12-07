package com.example.domain.entities

import com.example.domain.util.InvalidDataException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.time.LocalDateTime
import java.time.Month
import java.time.OffsetDateTime


class CarTest {

    @Test
    fun validateLicensePlateloweCase_licensePlateMathWithRegex_trueCreateObject() {
        //ARRANGE
        val licensePLate = "bzy44g"
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 7, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        // ACT
        val car = Car(licencePlate = licensePLate, dateEnter = date, dateExit = 0)
        assertNotNull(car)
    }

    @Test
    fun validateLicensePlateCamelCase_licensePlateMathWithRegex_trueCreateObject() {
        //ARRANGE
        val licensePLate = "bZy44G"
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 7, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        // ACT
        val car = Car(licencePlate = licensePLate, dateEnter = date, dateExit = 0)
        assertNotNull(car)
    }

    @Test
    fun validateLicensePlateUPPERCAR_licensePlateMathWithRegex_trueCreateObject() {
        //ARRANGE
        val licensePLate = "BZY44G"
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 7, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        // ACT
        val car = Car(licencePlate = licensePLate, dateEnter = date, dateExit = 0)
        assertNotNull(car)
    }

    @Test
    fun validateLicense_with7Characters_exception() {
        //ARRANGE
        val licensePLate = "bzy44gg"
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 7, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        // ACT
        try {
            Car(licencePlate = licensePLate, dateEnter = date, dateExit = 0)
        } catch (e: InvalidDataException) {
            //Assert
            assertEquals("No tiene el estandar de la placa", e.message)
        }
    }

    @Test
    fun validateLicense_withOnlyLetters_exception() {
        //ARRANGE
        val licensePLate = "abfjii"
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 7, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        // ACT
        try {
            Car(licencePlate = licensePLate, dateEnter = date, dateExit = 0)
        } catch (e: InvalidDataException) {
            //Assert
            assertEquals("No tiene el estandar de la placa", e.message)
        }
    }

    @Test
    fun validateInitialLetterA_CarDifferentToSundayAndMonday_expcetion() {
        //ARRANGE
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 7, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        // ACT
        try {
            Car(licencePlate = "AAA572", dateEnter = date, dateExit = 0)
        } catch (e: InvalidDataException) {
            //ASSERT
            assertEquals("No tiene permitido el ingreso", e.message)
        }
    }

    @Test
    fun validateInitialLetterA_CarSunday_createObject() {
        //ARRANGE
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 5, 5, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        //ACT
        val car = Car(licencePlate = "AAA572", dateEnter = date, dateExit = 0)
        //ASSERT
        assertNotNull(car)
    }
}