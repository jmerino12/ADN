package com.example.usecases

import com.example.data.repository.CarRepository
import com.example.domain.entities.Car
import com.example.testshared.mockedCar
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
class GetCarsTest {
    @Mock
    private lateinit var carRepository: CarRepository

    lateinit var getCars: GetCars

    @Before
    fun setUp() {
        getCars = GetCars(carRepository)
    }

    @Test
    fun invoke_getListOfCar() = runBlocking {
        //Arrange
        val offSetDateTime = OffsetDateTime.now()
        val date = LocalDateTime.of(2021, Month.DECEMBER, 5, 13, 0).toInstant(offSetDateTime.offset)
            .toEpochMilli()
        val cars = listOf(mockedCar, Car(licencePlate = "AFH328", dateEnter = date))
        //Act
        Mockito.`when`(carRepository.getCars()).thenReturn(cars)
        val result = getCars.invoke()

        //Assert
        assertEquals(cars, result)
    }
}