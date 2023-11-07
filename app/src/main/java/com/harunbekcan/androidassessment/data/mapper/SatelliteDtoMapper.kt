package com.harunbekcan.androidassessment.data.mapper

import com.harunbekcan.androidassessment.data.model.Position
import com.harunbekcan.androidassessment.data.model.Positions
import com.harunbekcan.androidassessment.data.model.SatelliteDetail
import com.harunbekcan.androidassessment.domain.model.PositionUiModel
import com.harunbekcan.androidassessment.domain.model.PositionsUiModel
import com.harunbekcan.androidassessment.domain.model.SatelliteDetailUiModel
import com.harunbekcan.androidassessment.utils.orEmptyList
import com.harunbekcan.androidassessment.utils.orZero

fun SatelliteDetail.toSatelliteDetailUiModel() = SatelliteDetailUiModel(
    id = id.orZero(),
    costPerLaunch = costPerLaunch.orZero(),
    firstFlight = firstFlight.orEmpty(),
    height = height.orZero(),
    mass = mass.orZero(),
)

fun Positions.toPositionsUiModel() = PositionsUiModel(
    id = id.orZero(),
    positions = positions.orEmptyList().map { it?.toPositionUiModel()?:PositionUiModel() }
)

fun Position.toPositionUiModel() = PositionUiModel(
    posX = posX.orZero(),
    posY = posY.orZero()
)