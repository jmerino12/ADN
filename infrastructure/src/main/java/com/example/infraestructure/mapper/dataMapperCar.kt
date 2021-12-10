package com.example.infraestructure.mapper

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