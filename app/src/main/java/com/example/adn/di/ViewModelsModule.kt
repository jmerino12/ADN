package com.example.adn.di


import com.example.adn.ui.viewmodels.CarViewModel
import com.example.adn.ui.viewmodels.MotorcycleViewModel
import com.example.usecaseslibrary.*
import dagger.Module
import dagger.Provides

@Module
class ViewModelsModule {
    @Provides
    fun carViewModelProvider(getCars: GetCars, saveCar: SaveCar, payCarParking: PayCarParking) =
        CarViewModel(getCars, saveCar, payCarParking)

    @Provides
    fun motorcycleViewModelProvider(
        getMotorcycles: GetMotorcycles,
        saveMotorcycle: SaveMotorcycle,
        payMotorcycleParking: PayMotorcycleParking
    ) =
        MotorcycleViewModel(getMotorcycles, saveMotorcycle, payMotorcycleParking)
}