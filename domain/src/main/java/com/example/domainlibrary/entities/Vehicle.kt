package com.example.domainlibrary.entities


import com.example.domainlibrary.exceptions.InvalidDataException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern
import kotlin.math.abs


abstract class Vehicle(
    val licencePlate: String,
    val cylinderCapacity: Double,
    val dateEnter: Long
) {

    init {

        if (!validateLicencePlate()) {
            throw InvalidDataException("No tiene el estandar de la placa")
        }

        if (!canEnter(dateEnter)) {
            throw InvalidDataException("No tiene permitido el ingreso")
        }


    }

    private fun validateLicencePlate(): Boolean {
        return (Pattern.matches("^[A-Z]{3}+[0-9]{2}[a-zA-Z0-9]\$", this.licencePlate.uppercase()))
    }

    private fun canEnter(dateNow: Long): Boolean {
        return if (licencePlate[0].equals("A".single(), true)) {
            dayDeniedToEnter(dateNow)
        } else {
            true
        }
    }

    fun isCylinderCapacityMore500(): Boolean {
        return cylinderCapacity >= 500
    }

    private fun dayDeniedToEnter(date: Long): Boolean {
        return when (SimpleDateFormat("EEEE", Locale.forLanguageTag("es")).format(date)) {
            "domingo" -> true
            "lunes" -> true
            else -> {
                false
            }
        }
    }

    private fun getDiff(): Long {
        return abs(dateEnter - System.currentTimeMillis())
    }

    private fun diffToHour(): Long {
        return if (TimeUnit.MILLISECONDS.toHours(getDiff()) < 1) 1 else TimeUnit.MILLISECONDS.toHours(
            getDiff()
        )
    }


    abstract fun payParking(): Double


    fun calculatePaymentParking(valuePerDay: Double, valuePerHour: Double): Double {
        var getHours = diffToHour()
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