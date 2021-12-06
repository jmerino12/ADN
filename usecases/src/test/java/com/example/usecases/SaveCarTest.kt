package com.example.usecases

import com.example.data.repository.CarRepository
import com.example.testshared.mockedCar
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class SaveCarTest {

    @Mock
    private lateinit var carRepository: CarRepository

    lateinit var saveCar: SaveCar

    @Before
    fun setUp() {
        saveCar = SaveCar(carRepository)
    }

    @Test
    fun saveCar() = runBlocking {
        val car = mockedCar
        saveCar.invoke(car)
        Mockito.verify(carRepository).saveCar(car)
    }


}