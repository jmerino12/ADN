package com.example.adn.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adn.common.Resource
import com.example.domain.vehicle.entities.Car
import com.example.usecases.GetCars
import com.example.usecases.PayCarParking
import com.example.usecases.SaveCar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CarViewModel(
    private val getCars: GetCars,
    private val saveCar: SaveCar,
    private val payCarParking: PayCarParking
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

    private val _eventMessageError = MutableLiveData<Exception?>()
    val eventMessageError: MutableLiveData<Exception?>
        get() = _eventMessageError

    private fun getListCar() {
        uiScope.launch {
            try {
                _car.value = Resource.Content(getCars.invoke())
            } catch (e: Exception) {
                _eventMessageError.value = e
            }
        }
    }

    fun saveCar(car: Car) {
        uiScope.launch {
            try {
                saveCar.invoke(car)
                getListCar()
            } catch (e: Exception) {
                _eventMessageError.value = e
            }
        }
    }

    fun payParking(car: Car) {
        uiScope.launch {
            payCarParking.invoke(car)
            getListCar()
        }
    }

    fun confirmMessage() {
        _eventMessageError.value = null
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}
