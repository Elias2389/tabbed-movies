package com.ae.tabbedmovies.dto

/**
 * Resource to response request to APIS
 *
 * @param T Generic type
 * @property status of transaction
 * @property data
 * @property message
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)
    }
}

/**
 * Enum about status
 * of Resource
 */
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}