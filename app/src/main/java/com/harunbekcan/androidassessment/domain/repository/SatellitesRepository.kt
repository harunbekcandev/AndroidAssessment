package com.harunbekcan.androidassessment.domain.repository

import com.harunbekcan.androidassessment.data.model.Satellite
import kotlinx.coroutines.flow.Flow

interface SatellitesRepository {
    fun getSatellites(): Flow<List<Satellite>>
}