package com.example.adn

import android.app.Application
import com.example.adn.di.DaggerMyAppComponent
import com.example.adn.di.MyAppComponent

class AdnApp : Application() {

    lateinit var component: MyAppComponent
        private set


    override fun onCreate() {
        super.onCreate()
        component = DaggerMyAppComponent
            .factory().create(this)
    }
}