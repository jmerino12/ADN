package com.example.adn.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.entities.Motorcycle
import com.example.usecases.GetMotorcycles
import com.example.usecases.SaveMotorcycle
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
class MotorcycleViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getMotorcycles: GetMotorcycles

    @Mock
    lateinit var saveMotorcycle: SaveMotorcycle

    @Mock
    lateinit var observer: Observer<List<Motorcycle>>

    private lateinit var motorcycleViewModel: MotorcycleViewModel

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        motorcycleViewModel = MotorcycleViewModel(getMotorcycles, saveMotorcycle)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getMotorcycles_observeLiveDate_success() = runBlocking {
        val bike = listOf(
            Motorcycle(
                licencePlate = "BEJ043",
                dateEnter = System.currentTimeMillis(),
                cylinderCapacity = 400.0
            )
        )
        Mockito.`when`(getMotorcycles.invoke()).thenReturn(bike)

        motorcycleViewModel.motorcycle.observeForever(observer)
        motorcycleViewModel.getListMotorCycle()

        Mockito.verify(observer).onChanged(bike)

    }

    @Test
    fun saveCar_success() = runBlocking {
        val bike = Motorcycle(
            licencePlate = "BEJ043",
            dateEnter = System.currentTimeMillis(),
            cylinderCapacity = 400.0
        )
        Mockito.`when`(saveMotorcycle.invoke(bike)).thenReturn(print("Saved"))
        motorcycleViewModel.saveMotorcycle(bike)
        Mockito.verify(saveMotorcycle).invoke(bike)
    }
}