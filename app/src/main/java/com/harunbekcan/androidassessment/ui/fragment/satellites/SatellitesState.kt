package com.harunbekcan.androidassessment.ui.fragment.satellites

import com.harunbekcan.androidassessment.data.model.Satellite

data class SatellitesState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val data: List<Satellite> = emptyList()
)