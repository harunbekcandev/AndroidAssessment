package com.harunbekcan.androidassessment.domain.repository

import com.harunbekcan.androidassessment.data.model.Satellite
import com.harunbekcan.androidassessment.domain.model.PositionsUiModel
import com.harunbekcan.androidassessment.domain.model.SatelliteDetailUiModel
import kotlinx.coroutines.flow.Flow

interface SatellitesRepository {
    fun getSatellites(): Flow<List<Satellite>>

    fun getSatelliteDetail(satelliteId: Int): Flow<SatelliteDetailUiModel>

    fun getSatellitePosition(satelliteId: Int): Flow<PositionsUiModel>
}