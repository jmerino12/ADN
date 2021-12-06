package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.domain.entities.Car
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class CarRepositoryTest {
    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var carRepository: CarRepository

    @Before
    fun setUp() {
        carRepository = CarRepository(localDataSource)
    }

    private val mockedCar = Car("BBB545", dateEnter = System.currentTimeMillis())

    @Test
    fun getListCar() = runBlocking {
        val data = listOf(mockedCar)
        Mockito.`when`(localDataSource.getListCars()).thenReturn(data)
        val result = carRepository.getCars()
        assertEquals(data, result)
    }

    @Test
    fun saveCarLocalIntoRoom() = runBlocking {

        val car = mockedCar
        Mockito.`when`(localDataSource.saveCar(car)).thenReturn(println("Insertado"))
        carRepository.saveCar(car)
        Mockito.verify(localDataSource).saveCar(car)

    }
}