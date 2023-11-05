package com.harunbekcan.androidassessment.domain.usecase

import com.harunbekcan.androidassessment.domain.repository.SatellitesRepository
import com.harunbekcan.androidassessment.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SatellitesUseCase @Inject constructor(private val satellitesRepository: SatellitesRepository) {

    operator fun invoke() = flow {
            emit(Resource.Loading)
            satellitesRepository.getSatellites().catch { e->
                emit(Resource.Error(e.message))
            }.collect {
               emit(Resource.Success(it))
            }
    }
}