package com.example.adn.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adn.common.UiState
import com.example.domain.vehicle.entities.Car
import com.example.usecases.GetCars
import com.example.usecases.PayCarParking
import com.example.usecases.SaveCar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CarViewModel(
    private val getCars: GetCars,
    private val saveCar: SaveCar,
    private val payCarParking: PayCarParking
) :
    ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _uiState = MutableStateFlow<UiState<List<Car>>>(value = UiState.Loading)
    val uiState: StateFlow<UiState<List<Car>>> = _uiState

    init {
        getListCar()
    }

    private fun getListCar() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                getCars.invoke().collect { vehicles ->
                    _uiState.value = UiState.Success(vehicles)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e)
            }
        }
    }

    fun saveCar(car: Car) {
        uiScope.launch {
            _uiState.value = UiState.Loading
            try {
                saveCar.invoke(car)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e)

            }
        }
    }

    fun payParking(car: Car) {
        uiScope.launch {
            _uiState.value = UiState.Loading
            try {
                payCarParking.invoke(car)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e)
            }
        }
    }

    fun clearMessageError() {
        _uiState.value = UiState.Error(null)
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}
