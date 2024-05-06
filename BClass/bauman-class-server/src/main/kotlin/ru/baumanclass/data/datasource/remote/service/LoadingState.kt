package ru.baumanclass.data.datasource.remote.service

sealed interface LoadingState {
    class Success(val message: String) : LoadingState
    class Error(val error: NetworkError) : LoadingState
}
