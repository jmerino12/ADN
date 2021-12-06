package com.example.adn.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Car::class, Motorcycle::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}
