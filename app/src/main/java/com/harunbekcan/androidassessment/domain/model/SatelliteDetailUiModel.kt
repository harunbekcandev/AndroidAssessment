package com.harunbekcan.androidassessment.domain.model

data class SatelliteDetailUiModel(
    val id: Int = 0,
    val costPerLaunch: Long = 0,
    val firstFlight: String = "",
    val height: Int = 0,
    val mass: Long = 0
)