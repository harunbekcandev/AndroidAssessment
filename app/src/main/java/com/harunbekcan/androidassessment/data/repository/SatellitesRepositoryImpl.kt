package com.harunbekcan.androidassessment.data.repository

import com.google.gson.Gson
import com.harunbekcan.androidassessment.data.mapper.toPositionsUiModel
import com.harunbekcan.androidassessment.data.mapper.toSatelliteDetailUiModel
import com.harunbekcan.androidassessment.data.model.Satellite
import com.harunbekcan.androidassessment.data.model.SatelliteDetail
import com.harunbekcan.androidassessment.data.model.SatellitePositionsList
import com.harunbekcan.androidassessment.data.source.local.SatellitesDao
import com.harunbekcan.androidassessment.di.SatelliteDetailJson
import com.harunbekcan.androidassessment.di.SatelliteListJson
import com.harunbekcan.androidassessment.di.SatellitePositionsJson
import com.harunbekcan.androidassessment.domain.repository.SatellitesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SatellitesRepositoryImpl @Inject constructor(
    @SatelliteListJson private val getSatelliteList: String,
    @SatelliteDetailJson private val getSatelliteDetailJson: String,
    @SatellitePositionsJson private val getSatellitePositions: String,
    private val satellitesDao: SatellitesDao,
    private val gson: Gson
) : SatellitesRepository {

    override fun getSatellites(): Flow<List<Satellite>> {
        return flow {
            val satelliteList = gson.fromJson(getSatelliteList, Array<Satellite>::class.java).toList()
            emit(satelliteList)
        }
    }

    override fun getSatelliteDetail(satelliteId: Int) = flow {

        val satelliteDetail = satellitesDao.getSatelliteDetails(satelliteId)

        if (satelliteDetail != null) {
            emit(satelliteDetail.toSatelliteDetailUiModel())
        } else {
            gson.fromJson(
                getSatelliteDetailJson,
                Array<SatelliteDetail>::class.java
            ).find { it.id == satelliteId }?.let {
                satellitesDao.insertSatelliteDetail(it)
                emit(it.toSatelliteDetailUiModel())
            }
        }
    }

    override fun getSatellitePosition(satelliteId: Int) = flow {
        val satellitePosition = satellitesDao.getSatellitePositions(satelliteId)

        if (satellitePosition != null) {
            emit(satellitePosition.toPositionsUiModel())
        } else {
            gson.fromJson(
                getSatellitePositions,
                SatellitePositionsList::class.java
            ).list?.find { it?.id == satelliteId }?.let {
                satellitesDao.insertSatellitePosition(it)
                emit(it.toPositionsUiModel())
            }
        }
    }
}