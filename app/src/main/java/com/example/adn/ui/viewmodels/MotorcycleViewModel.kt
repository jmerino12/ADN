package com.example.adn.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adn.common.UiState
import com.example.domain.vehicle.entities.Motorcycle
import com.example.usecases.GetMotorcycles
import com.example.usecases.PayMotorcycleParking
import com.example.usecases.SaveMotorcycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MotorcycleViewModel(
    private val getMotorcycles: GetMotorcycles,
    private val saveMotorcycle: SaveMotorcycle,
    private val payMotorcycleParking: PayMotorcycleParking
) :
    ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _uiState = MutableStateFlow<UiState<List<Motorcycle>>>(value = UiState.Loading)
    val uiState: StateFlow<UiState<List<Motorcycle>>> = _uiState

    init {
        getMotorcycles()
    }

    private fun getMotorcycles() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                getMotorcycles.invoke().collect { vehicles ->
                    _uiState.value = UiState.Success(vehicles)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e)
                Log.e("MotocycleViewModel", e.toString())
            }
        }

    }

    fun saveMotorcycle(motorcycle: Motorcycle) {
        uiScope.launch {
            _uiState.value = UiState.Loading
            try {
                saveMotorcycle.invoke(motorcycle)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e)
            }
        }
    }

    fun payParking(motorcycle: Motorcycle) {
        uiScope.launch {
            _uiState.value = UiState.Loading
            try {
                payMotorcycleParking.invoke(motorcycle)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e)
            }
        }
    }


    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}
