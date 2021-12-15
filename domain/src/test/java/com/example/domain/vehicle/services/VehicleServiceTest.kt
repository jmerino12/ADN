package com.example.domain.vehicle.services

import com.example.domain.Parking
import com.example.domain.shared.exceptions.LimitParkingException
import com.example.domain.shared.exceptions.VehicleExistsException
import com.example.domain.vehicle.entities.Motorcycle
import com.example.domain.vehicle.repositories.VehicleRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VehicleServiceTest {

    @Mock
    lateinit var vehicleRepository: VehicleRepository<Motorcycle>

    @Mock
    lateinit var parking: Parking


    private lateinit var vehicleService: VehicleService<Motorcycle>

    @Before
    fun setUp() {
        vehicleService = VehicleService(vehicleRepository, parking)
    }

    @Test
    fun getVehicles_invoke_success() = runBlocking {
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
        whenever(vehicleService.getVehicles()).thenReturn(listMotorcycles)
        val result = vehicleRepository.getVehicles()
        assertEquals(listMotorcycles, result)
    }


    @Test
    fun saveVehicle_motorcycleInRoom_success() = runBlocking {
        val newMotorcycle =
            Motorcycle("BDD431", System.currentTimeMillis(), cylinderCapacity = 200.0)
        vehicleRepository.saveVehicle(newMotorcycle)
        verify(vehicleRepository).saveVehicle(newMotorcycle)

    }

    @Test
    fun payParking_motorcycle_deleteInRoom() = runBlocking {
        val newMotorcycle =
            Motorcycle("BDD431", System.currentTimeMillis(), cylinderCapacity = 200.0)
        vehicleService.payParking(newMotorcycle)
        verify(vehicleRepository).payParking(newMotorcycle)
    }

    @Test(expected = VehicleExistsException::class)
    fun saveVehicle_motorcycleInRoom_exceptionExist() = runBlocking {
        val newMotorcycle =
            Motorcycle("BDD431", System.currentTimeMillis(), cylinderCapacity = 200.0)
        whenever(vehicleRepository.countVehicleInParking()).thenReturn(4)
        whenever(vehicleRepository.existVehicle(any())).thenReturn(true)
        verify(vehicleService.saveVehicle(newMotorcycle))

    }

    @Test(expected = LimitParkingException::class)
    fun saveVehicle_motorcycleInRoom_exceptionLimitParking() = runBlocking {
        val newMotorcycle =
            Motorcycle("BDD431", System.currentTimeMillis(), cylinderCapacity = 200.0)
        whenever(vehicleRepository.countVehicleInParking()).thenReturn(10)
        whenever(parking.isParkingFull(10, newMotorcycle)).thenReturn(true)
        verify(vehicleService.saveVehicle(newMotorcycle))
    }


}