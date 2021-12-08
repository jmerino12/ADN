package com.example.adn

import com.example.domainlibrary.entities.Car
import com.example.domainlibrary.entities.Motorcycle
import com.example.adn.model.database.Car as CarRoom
import com.example.adn.model.database.Motorcycle as MotorCyclerRoom


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




