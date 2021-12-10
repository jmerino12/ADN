package com.example.infraestructure.database

import com.example.domain.entities.Car
import com.example.infraestructure.database.entity.Car as CarRoom

fun Car.toRoomVehicle(): CarRoom = CarRoom(
    licencePlate = licencePlate,
    dateEnter = dateEnter
)

fun CarRoom.toDomain(): Car = Car(
    licencePlate = licencePlate,
    dateEnter = dateEnter,
)