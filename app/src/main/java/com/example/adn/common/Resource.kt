package com.example.adn.common

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Content<out T>(val data: T) : Resource<T>()
    class Error(val error: Exception) : Resource<Nothing>()
}