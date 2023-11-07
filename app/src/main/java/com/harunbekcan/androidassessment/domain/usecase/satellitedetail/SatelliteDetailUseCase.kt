package com.harunbekcan.androidassessment.domain.usecase.satellitedetail

import com.harunbekcan.androidassessment.domain.repository.SatellitesRepository
import com.harunbekcan.androidassessment.utils.Resource
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SatelliteDetailUseCase @Inject constructor(
    private val satellitesRepository: SatellitesRepository,
    private val getSatellitePositionsUseCase: GetSatellitePositionsUseCase
) {

    operator fun invoke(satelliteId: Int) = flow {

        val satelliteDetail = satellitesRepository.getSatelliteDetail(satelliteId)
        val satellitePositions = getSatellitePositionsUseCase(satelliteId)

        satelliteDetail.combine(satellitePositions) { detail, position ->
            Resource.Loading
            try {
                Resource.Success(Pair(detail, position))
            } catch (e: Exception) {
                Resource.Error(e.message.orEmpty())
            }
        }.collect {
            emit(it)
        }
    }
}