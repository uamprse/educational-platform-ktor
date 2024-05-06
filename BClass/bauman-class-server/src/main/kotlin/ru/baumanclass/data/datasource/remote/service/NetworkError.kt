package ru.baumanclass.data.datasource.remote.service

sealed class NetworkError(private val messageId: String) : RuntimeException() {
    data object UnknownError : NetworkError("Неизвестная ошибка")
    data object DataNotFoundError : NetworkError("Запраашиваемые данные не были найдены")
    data object ServerError : NetworkError("Сетевая ошибка")
}
