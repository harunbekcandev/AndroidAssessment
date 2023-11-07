package com.harunbekcan.androidassessment.domain.model

data class PositionsUiModel(
    val id: Int = 0,
    val positions: List<PositionUiModel> = emptyList()
)