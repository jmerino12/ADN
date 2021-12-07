package com.example.adn.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entities.Car
import com.example.usecases.GetCars
import com.example.usecases.SaveCar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CarViewModel(
    private val getCars: GetCars,
    private val saveCar: SaveCar
) :
    ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _cars = MutableLiveData<List<Car>>()
    val cars: LiveData<List<Car>> get() = _cars

    fun getListCar() {
        uiScope.launch {
            _cars.value = getCars.invoke()
        }
    }

    fun saveCar(car: Car) {
        uiScope.launch {
            saveCar.invoke(car)
        }
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}
