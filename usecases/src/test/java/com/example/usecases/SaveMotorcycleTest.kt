package com.example.usecases

import com.example.data.repository.MotorcycleRepository
import com.example.domain.entities.Motorcycle
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class SaveMotorcycleTest {
    @Mock
    private lateinit var motorcycleRepository: MotorcycleRepository

    lateinit var saveMotorcycle: SaveMotorcycle

    private val mockedBike =
        Motorcycle("BBB545", dateEnter = System.currentTimeMillis(), cylinderCapacity = 400.0)

    @Before
    fun setUp() {
        saveMotorcycle = SaveMotorcycle(motorcycleRepository)
    }

    @Test
    fun saveCar() = runBlocking {
        val bike = mockedBike
        saveMotorcycle.invoke(bike)
        Mockito.verify(motorcycleRepository).saveMotorcycle(bike)
    }
}