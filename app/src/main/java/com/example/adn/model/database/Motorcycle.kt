package com.example.adn.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Motorcycle(
    @PrimaryKey
    val licencePlate: String,
    val cylinderCapacity: Double,
    val dateEnter: Long

)