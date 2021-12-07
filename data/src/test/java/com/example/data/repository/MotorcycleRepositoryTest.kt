package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.domain.entities.Motorcycle
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class MotorcycleRepositoryTest {
    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var motorcycleRepository: MotorcycleRepository

    @Before
    fun setUp() {
        motorcycleRepository = MotorcycleRepository(localDataSource)
    }

    private val mockedMotorcycle =
        Motorcycle("BBB545", dateEnter = System.currentTimeMillis(), cylinderCapacity = 400.0)

    @Test
    fun getListCar() = runBlocking {
        val data = listOf(mockedMotorcycle)
        Mockito.`when`(localDataSource.getListMotorcycle()).thenReturn(data)
        val result = motorcycleRepository.getMotorcycles()
        assertEquals(data, result)
    }

    @Test
    fun saveCarLocalIntoRoom() = runBlocking {

        val bike = mockedMotorcycle
        Mockito.`when`(localDataSource.saveMotorcycle(bike)).thenReturn(println("Insertado"))
        motorcycleRepository.saveMotorcycle(bike)
        Mockito.verify(localDataSource).saveMotorcycle(bike)

    }
}