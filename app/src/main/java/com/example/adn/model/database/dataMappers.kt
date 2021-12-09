package com.example.adn.model.database

import com.example.domain.entities.Car
import com.example.domain.entities.Motorcycle
import com.example.adn.model.database.entity.Car as CarRoom
import com.example.adn.model.database.entity.Motorcycle as MotorCyclerRoom


fun Car.toRoomVehicle(): CarRoom = CarRoom(
    licencePlate = licencePlate,
    cylinderCapacity = cylinderCapacity,
    dateEnter = dateEnter
)

fun CarRoom.toDomain(): Car = Car(
    licencePlate = licencePlate,
    cylinderCapacity = cylinderCapacity,
    dateEnter = dateEnter,
)

fun Motorcycle.toRoomVehicle(): MotorCyclerRoom = MotorCyclerRoom(
    cylinderCapacity = cylinderCapacity,
    licencePlate = licencePlate,
    dateEnter = dateEnter
)

fun MotorCyclerRoom.toDomain(): Motorcycle = Motorcycle(
    licencePlate = licencePlate,
    cylinderCapacity = cylinderCapacity,
    dateEnter = dateEnter
)




