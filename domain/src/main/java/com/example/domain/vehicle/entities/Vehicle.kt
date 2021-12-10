package com.example.domain.vehicle.entities

import com.example.domain.shared.exceptions.InvalidDataException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern
import kotlin.math.abs

abstract class Vehicle(
    val licencePlate: String,
    val dateEnter: Long
) {
    private val REGEX_LICENCE_PLATE = "^[A-Z]{3}+[0-9]{2}[a-zA-Z0-9]\$"
    private val EXCEPTION_VALIDATE_LICENCE_PLATE = "No tiene el estandar de la placa"
    private val EXCEPTION_VALIDATE_ENTER = "No tiene permitido el ingreso"
    private val INTIAL_LICENSE_PLATE = "A"
    private val DAY_DENIED_SUNDAY = "domingo"
    private val DAY_DENIED_MONDAY = "lunes"

    init {
        if (!validateLicencePlate()) {
            throw InvalidDataException(EXCEPTION_VALIDATE_LICENCE_PLATE)
        }

        if (!canEnter(dateEnter)) {
            throw InvalidDataException(EXCEPTION_VALIDATE_ENTER)
        }
    }

    private fun validateLicencePlate(): Boolean {
        return (Pattern.matches(REGEX_LICENCE_PLATE, this.licencePlate.uppercase()))
    }

    private fun canEnter(dateNow: Long): Boolean {
        return if (licencePlate[0].equals(INTIAL_LICENSE_PLATE.single(), true)) {
            dayDeniedToEnter(dateNow)
        } else {
            true
        }
    }

    private fun dayDeniedToEnter(date: Long): Boolean {
        return when (SimpleDateFormat("EEEE", Locale.forLanguageTag("es")).format(date)) {
            DAY_DENIED_SUNDAY -> true
            DAY_DENIED_MONDAY -> true
            else -> {
                false
            }
        }
    }

    private fun getDiffBetweenDateEnterAndExitDate(): Long {
        return abs(dateEnter - System.currentTimeMillis())
    }

    private fun diffToHourBetweenDateEnterAndExitDate(): Long {
        return if (TimeUnit.MILLISECONDS.toHours(getDiffBetweenDateEnterAndExitDate()) < 1) 1 else TimeUnit.MILLISECONDS.toHours(
            getDiffBetweenDateEnterAndExitDate()
        )
    }

    abstract fun payParking(): Double

    fun calculatePaymentParking(valuePerDay: Double, valuePerHour: Double): Double {
        var getHours = diffToHourBetweenDateEnterAndExitDate()
        var daysToPay = 0L
        var hoursToPay = 0L
        if ((getHours / 24) > 0) {
            daysToPay = getHours / 24
            getHours %= 24
        }
        if (getHours >= 9) {
            daysToPay++
        } else {
            hoursToPay = getHours
        }
        return (daysToPay * valuePerDay) + (hoursToPay * valuePerHour)
    }
}