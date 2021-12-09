package com.example.adn.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adn.common.Resource
import com.example.domain.entities.Car
import com.example.domain.services.CarService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CarViewModel(
    private val carService: CarService
) :
    ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _car = MutableLiveData<Resource<List<Car>>>()
    val car: LiveData<Resource<List<Car>>>
        get() {
            if (_car.value == null) getListCar()
            return _car
        }

    private fun getListCar() {
        uiScope.launch {
            try {
                _car.value = Resource.Content(carService.getCars())
            } catch (e: Exception) {
                _car.value = Resource.Error(e)
            }
        }
    }

    fun saveCar(car: Car) {
        uiScope.launch {
            carService.saveCar(car)
            getListCar()
        }
    }

    fun payParking(car: Car) {
        uiScope.launch {
            carService.payParking(car)
            getListCar()
        }
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}
