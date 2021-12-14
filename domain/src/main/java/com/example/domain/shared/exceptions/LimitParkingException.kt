package com.example.domain.shared.exceptions

class LimitParkingException(message: String = "NO PUEDE INGRESAR, ESTA EN SU CAPACIDAD MAXIMA") :
    RuntimeException(message)