package com.example.usecases

import com.example.data.repository.MotorcycleRepository
import com.example.domain.entities.Motorcycle
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDateTime
import java.time.Month
import java.time.OffsetDateTime

@RunWith(MockitoJUnitRunner.Silent::class)
class GetMotorcyclesTest {
    @Mock
    private lateinit var motorcycleRepository: MotorcycleRepository

    private lateinit var getMotorcycles: GetMotorcycles

    @Before
    fun setUp() {
        getMotorcycles = GetMotorcycles(motorcycleRepository)
    }

    @Test
    fun invoke_getListOfCar() = runBlocking {
        //Arrange
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 5, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        val bike =
            listOf(Motorcycle(licencePlate = "AFH328", dateEnter = date, cylinderCapacity = 400.0))
        //Act
        Mockito.`when`(motorcycleRepository.getMotorcycles()).thenReturn(bike)
        val result = getMotorcycles.invoke()

        //Assert
        assertEquals(bike, result)
    }
}