package com.ouvrirdeveloper.domain.models

sealed class UiState {

    object INIIAL_STATE : UiState()
    object IN_PROGRESS : UiState()
    object COMPLETE : UiState()
    data class SHOW_RETRY(val text: Int) : UiState()
    data class SHOW_ERROR(val text: Int) : UiState()
    object NETWORK_ERROR : UiState()



}