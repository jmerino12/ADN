package com.example.infraestructure.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Motorcycle(
    @PrimaryKey
    val licencePlate: String,
    val cylinderCapacity: Double,
    val dateEnter: Long

)