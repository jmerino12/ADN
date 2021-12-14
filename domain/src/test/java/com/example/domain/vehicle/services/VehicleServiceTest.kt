package com.example.domain.vehicle.services

import com.example.domain.Parking
import com.example.domain.shared.exceptions.VehicleExistsException
import com.example.domain.vehicle.entities.Motorcycle
import com.example.domain.vehicle.repositories.VehicleRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VehicleServiceTest {

    @Mock
    lateinit var vehicleRepository: VehicleRepository<Motorcycle>

    @Mock
    lateinit var parking: Parking

    @Mock
    lateinit var vehicleExistsException: VehicleExistsException

    lateinit var vehicleService: VehicleService<Motorcycle>

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
        Mockito.`when`(vehicleService.getVehicles()).thenReturn(listMotorcycles)
        val result = vehicleRepository.getVehicles()
        assertEquals(listMotorcycles, result)
    }

    @Test
    fun payParkingMotorcycle_success() = runBlocking {
        val newMotorcycle =
            Motorcycle("BDD431", System.currentTimeMillis(), cylinderCapacity = 200.0)
        vehicleRepository.payParking(newMotorcycle)
        Mockito.verify(vehicleRepository).payParking(newMotorcycle)

    }

    @Test
    fun saveVehicle_motorcycleInRoom_success() = runBlocking {
        val newMotorcycle =
            Motorcycle("BDD431", System.currentTimeMillis(), cylinderCapacity = 200.0)
        vehicleRepository.saveVehicle(newMotorcycle)
        Mockito.verify(vehicleRepository).saveVehicle(newMotorcycle)

    }

    @Test
    fun saveVehicle_motorcycleInRoom_exceptionExist() = runBlocking {
        val newMotorcycle =
            Motorcycle("BDD431", System.currentTimeMillis(), cylinderCapacity = 200.0)
        Mockito.`when`(vehicleRepository.saveVehicle(newMotorcycle))
            .thenThrow(VehicleExistsException())
        val result = VehicleExistsException().message
        assertEquals(result, "YA EXISTE ESTE VEHICUL0")

    }


}