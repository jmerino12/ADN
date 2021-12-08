package com.example.adn.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adn.common.Resource
import com.example.domainlibrary.entities.Car
import com.example.usecaseslibrary.GetCars
import com.example.usecaseslibrary.SaveCar
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

    private val _car = MutableLiveData<Resource<List<Car>>>()
    val car: LiveData<Resource<List<Car>>>
        get() {
            if (_car.value == null) getListCar()
            return _car
        }

    private fun getListCar() {
        uiScope.launch {
            try {
                _car.value = Resource.Content(getCars.invoke())
            } catch (e: Exception) {
                _car.value = Resource.Error(e)
            }
        }
    }

    fun saveCar(car: Car) {
        uiScope.launch {
            saveCar.invoke(car)
            getListCar()
        }
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}
