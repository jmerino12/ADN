package com.example.adn.di

import com.example.adn.ui.viewmodels.CarViewModel
import com.example.adn.ui.viewmodels.MotorcycleViewModel
import com.example.usecases.GetCars
import com.example.usecases.GetMotorcycles
import com.example.usecases.SaveCar
import com.example.usecases.SaveMotorcycle
import dagger.Module
import dagger.Provides

@Module
class ViewModelsModule {
    @Provides
    fun carViewModelProvider(getCars: GetCars, saveCar: SaveCar) =
        CarViewModel(getCars, saveCar)

    @Provides
    fun motorcycleViewModelProvider(
        getMotorcycles: GetMotorcycles,
        saveMotorcycle: SaveMotorcycle
    ) =
        MotorcycleViewModel(getMotorcycles, saveMotorcycle)
}