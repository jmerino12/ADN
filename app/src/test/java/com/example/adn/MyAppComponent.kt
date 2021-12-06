package com.example.adn

import android.app.Application
import com.example.adn.di.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface MyAppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): MyAppComponent
    }
}
