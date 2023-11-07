package com.harunbekcan.androidassessment.ui.fragment.satellitedetail

import com.harunbekcan.androidassessment.domain.model.PositionUiModel
import com.harunbekcan.androidassessment.domain.model.SatelliteDetailUiModel

data class SatelliteDetailState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val satelliteName: String = "",
    val satelliteDetailUiModel: SatelliteDetailUiModel = SatelliteDetailUiModel(),
    val satellitePositionUIModel: PositionUiModel = PositionUiModel()
)