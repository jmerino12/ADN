package com.example.adn.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.entities.Car
import com.example.usecases.GetCars
import com.example.usecases.SaveCar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CarViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getCars: GetCars

    @Mock
    lateinit var saveCars: SaveCar

    @Mock
    lateinit var observer: Observer<List<Car>>

    private lateinit var carViewModel: CarViewModel

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        carViewModel = CarViewModel(getCars, saveCars)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getCars_observeLiveDate_success() = runBlocking {
        val cars = listOf(Car(licencePlate = "BEJ043", dateEnter = System.currentTimeMillis()))
        Mockito.`when`(getCars.invoke()).thenReturn(cars)

        carViewModel.cars.observeForever(observer)
        carViewModel.getListCar()

        Mockito.verify(observer).onChanged(cars)

    }

    @Test
    fun saveCar_success() = runBlocking {
        val car = Car(licencePlate = "BEJ043", dateEnter = System.currentTimeMillis())
        Mockito.`when`(saveCars.invoke(car)).thenReturn(print("Saved"))
        carViewModel.saveCar(car)
        Mockito.verify(saveCars).invoke(car)
    }
}