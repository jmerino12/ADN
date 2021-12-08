package com.example.adn.di


import com.example.adn.ui.viewmodels.CarViewModel
import com.example.adn.ui.viewmodels.MotorcycleViewModel
import com.example.usecaseslibrary.GetCars
import com.example.usecaseslibrary.GetMotorcycles
import com.example.usecaseslibrary.SaveCar
import com.example.usecaseslibrary.SaveMotorcycle
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