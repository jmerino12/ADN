package com.example.adn.di

import android.app.Application
import com.example.adn.ui.viewmodels.CarViewModel
import com.example.adn.ui.viewmodels.MotorcycleViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, InfrastructureModule::class, UseCaseModule::class, ViewModelsModule::class])
interface MyAppComponent {

    val carViewModel: CarViewModel
    val motorcycleViewModel: MotorcycleViewModel

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): MyAppComponent
    }
}