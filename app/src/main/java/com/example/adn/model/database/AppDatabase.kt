package com.example.adn.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.adn.model.database.dao.VehicleDao
import com.example.adn.model.database.entity.Car
import com.example.adn.model.database.entity.Motorcycle

@Database(entities = [Car::class, Motorcycle::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}
