package com.example.domain

import com.example.domain.shared.exceptions.LimitParkingException
import com.example.domain.vehicle.entities.Car
import com.example.domain.vehicle.entities.Motorcycle
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test


class ParkingTest {

    @Test
    fun isParkingCarFull_parkingMaximumCapacityCar_exception() {

        //ARRANGE
        val listCars = arrayListOf(
            Car("BDD431", System.currentTimeMillis()),
            Car("BDD432", System.currentTimeMillis()),
            Car("BDD433", System.currentTimeMillis()),
            Car("BDD434", System.currentTimeMillis()),
            Car("BDD435", System.currentTimeMillis()),
            Car("BDD436", System.currentTimeMillis()),
            Car("BDD437", System.currentTimeMillis()),
            Car("BDD438", System.currentTimeMillis()),
            Car("BDD439", System.currentTimeMillis()),
            Car("BDD440", System.currentTimeMillis()),
            Car("BDD441", System.currentTimeMillis()),
            Car("BDD442", System.currentTimeMillis()),
            Car("BDD443", System.currentTimeMillis()),
            Car("BDD444", System.currentTimeMillis()),
            Car("BDD445", System.currentTimeMillis()),
            Car("BDD446", System.currentTimeMillis()),
            Car("BDD447", System.currentTimeMillis()),
            Car("BDD448", System.currentTimeMillis()),
            Car("BDD449", System.currentTimeMillis()),
            Car("BDD450", System.currentTimeMillis()),
        )
        val newCar = Car("BDD451", System.currentTimeMillis())
        val parking = Parking()

        //ACT
        try {
            parking.isParkingFull(listCars.size, newCar)
        } catch (e: LimitParkingException) {

            //ASSERT
            assertEquals("NO PUEDE INGRESAR, ESTA EN SU CAPACIDAD MAXIMA", e.message)
        }
    }

    @Test
    fun isParkingMotorcycleFull_parkingMaximumCapacityMotorcycle_exception() {

        //ARRANGE
        val listMotorcycles = arrayListOf(
            Motorcycle("BDD431", System.currentTimeMillis(), cylinderCapacity = 200.0),
            Motorcycle("BDD432", System.currentTimeMillis(), cylinderCapacity = 200.0),
            Motorcycle("BDD433", System.currentTimeMillis(), cylinderCapacity = 200.0),
            Motorcycle("BDD434", System.currentTimeMillis(), cylinderCapacity = 200.0),
            Motorcycle("BDD435", System.currentTimeMillis(), cylinderCapacity = 200.0),
            Motorcycle("BDD436", System.currentTimeMillis(), cylinderCapacity = 200.0),
            Motorcycle("BDD437", System.currentTimeMillis(), cylinderCapacity = 200.0),
            Motorcycle("BDD438", System.currentTimeMillis(), cylinderCapacity = 200.0),
            Motorcycle("BDD439", System.currentTimeMillis(), cylinderCapacity = 200.0),
            Motorcycle("BDD440", System.currentTimeMillis(), cylinderCapacity = 200.0),
        )
        val newMotorcycle =
            Motorcycle("BDD451", System.currentTimeMillis(), cylinderCapacity = 200.0)
        val parking = Parking()

        //ACT
        try {
            parking.isParkingFull(listMotorcycles.size, newMotorcycle)
        } catch (e: LimitParkingException) {

            //ASSERT
            assertEquals("NO PUEDE INGRESAR, ESTA EN SU CAPACIDAD MAXIMA", e.message)
        }
    }

    @Test
    fun isParkingMotorcycleNotFull_parkingNoMaximumCapacityMotorcycle_false() {

        //ARRANGE
        val listMotorcycles = arrayListOf(
            Motorcycle("BDD431", System.currentTimeMillis(), cylinderCapacity = 200.0),
            Motorcycle("BDD432", System.currentTimeMillis(), cylinderCapacity = 200.0),
            Motorcycle("BDD433", System.currentTimeMillis(), cylinderCapacity = 200.0),
            Motorcycle("BDD434", System.currentTimeMillis(), cylinderCapacity = 200.0),
            Motorcycle("BDD435", System.currentTimeMillis(), cylinderCapacity = 200.0),
            Motorcycle("BDD436", System.currentTimeMillis(), cylinderCapacity = 200.0),
            Motorcycle("BDD437", System.currentTimeMillis(), cylinderCapacity = 200.0),
            Motorcycle("BDD438", System.currentTimeMillis(), cylinderCapacity = 200.0),

            )
        val newMotorcycle =
            Motorcycle("BDD446", System.currentTimeMillis(), cylinderCapacity = 200.0)
        val parking = Parking()

        //ACT
        val result = parking.isParkingFull(listMotorcycles.size, newMotorcycle)
        //ASSERT
        assertFalse(result)
    }
}