package com.example.adn.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.domain.entities.Car
import com.example.usecases.GetCars
import com.example.usecases.SaveCar
import kotlinx.coroutines.*

class CarViewModel(
    private val getCars: GetCars,
    private val saveCar: SaveCar
) :
    ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getListCar() = liveData(Dispatchers.IO) {
        emit(getCars.invoke())
    }

    fun saveCar(car: Car) {
        uiScope.launch {
            saveCar.invoke(car)
            delay(2000)
            getListCar()
        }
    }

    override fun onCleared() {
        uiScope.cancel()
        viewModelJob.cancel()
        super.onCleared()
    }
}
