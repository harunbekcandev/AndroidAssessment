package com.harunbekcan.androidassessment.domain.usecase.satellitedetail

import com.harunbekcan.androidassessment.domain.repository.SatellitesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSatellitePositionsUseCase @Inject constructor(private val satellitesRepository: SatellitesRepository) {

    operator fun invoke(satelliteId: Int) = flow {
        satellitesRepository.getSatellitePosition(satelliteId).collect {
            while (true) {
                it.positions.forEachIndexed { _, position ->
                    emit(position)
                    delay(3000)
                }
            }
        }
    }
}