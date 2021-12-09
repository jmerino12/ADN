package com.example.infraestructure.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey
    val licencePlate: String,
    val cylinderCapacity: Double = 0.0,
    val dateEnter: Long
)