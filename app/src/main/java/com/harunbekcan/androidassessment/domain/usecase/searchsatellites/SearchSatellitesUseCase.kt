package com.harunbekcan.androidassessment.domain.usecase.searchsatellites

import com.harunbekcan.androidassessment.domain.repository.SatellitesRepository
import com.harunbekcan.androidassessment.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchSatellitesUseCase @Inject constructor(private val satellitesRepository: SatellitesRepository) {

    operator fun invoke(searchKey: String) = flow {
        if (searchKey.isNotEmpty()){
            emit(Resource.Loading)
        }
        satellitesRepository.getSatellites().catch { e ->
            emit(Resource.Error(e.message))
        }.collect {
            val filteredList = it.filter { data ->
                data.name.lowercase().contains(searchKey.lowercase())
            }
            emit(Resource.Success(filteredList))
        }
    }
}

