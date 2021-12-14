package com.example.adn.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adn.common.Resource
import com.example.domain.vehicle.entities.Motorcycle
import com.example.usecases.GetMotorcycles
import com.example.usecases.PayMotorcycleParking
import com.example.usecases.SaveMotorcycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MotorcycleViewModel(
    private val getMotorcycles: GetMotorcycles,
    private val saveMotorcycle: SaveMotorcycle,
    private val payMotorcycleParking: PayMotorcycleParking
) :
    ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _motorcycle = MutableLiveData<Resource<List<Motorcycle>>>()
    val motorcycle: LiveData<Resource<List<Motorcycle>>>
        get() {
            if (_motorcycle.value == null) getListMotorCycle()
            return _motorcycle
        }

    private val _eventMessageError = MutableLiveData<Exception?>()
    val eventMessageError: MutableLiveData<Exception?>
        get() = _eventMessageError


    private fun getListMotorCycle() {
        uiScope.launch {
            _motorcycle.value = Resource.Loading()
            try {
                _motorcycle.value = Resource.Content(getMotorcycles.invoke())
            } catch (e: Exception) {
                _eventMessageError.value = e
            }
        }
    }

    fun saveMotorcycle(motorcycle: Motorcycle) {
        uiScope.launch {
            try {
                saveMotorcycle.invoke(motorcycle)
                getListMotorCycle()
            } catch (e: Exception) {
                _eventMessageError.value = e
            }
        }
    }

    fun payParking(motorcycle: Motorcycle) {
        uiScope.launch {
            payMotorcycleParking.invoke(motorcycle)
            getListMotorCycle()
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
