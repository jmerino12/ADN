package com.example.infraestructure.vehicle.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

inline fun <T, R> Flow<Iterable<T>>.mapIterable(crossinline transform: suspend (T) -> R): Flow<List<R>> =
    map { list ->
        list.map { item ->
            transform(item)
        }
    }