package com.harunbekcan.androidassessment.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.harunbekcan.androidassessment.data.model.Satellite
import com.harunbekcan.androidassessment.di.SatelliteListJson
import com.harunbekcan.androidassessment.domain.repository.SatellitesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SatellitesRepositoryImpl @Inject constructor(
    @SatelliteListJson private val getSatelliteList: String,
) : SatellitesRepository {

    private val gson = Gson()

    override fun getSatellites() : Flow<List<Satellite>> {
        return flow {
            val satelliteListType = object : TypeToken<List<Satellite>>() {}.type
            val satelliteList: List<Satellite> = gson.fromJson(getSatelliteList, satelliteListType)
            emit(satelliteList)
        }
    }
}