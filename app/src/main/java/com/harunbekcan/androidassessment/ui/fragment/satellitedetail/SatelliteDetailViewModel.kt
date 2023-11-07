package com.harunbekcan.androidassessment.ui.fragment.satellitedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harunbekcan.androidassessment.domain.model.PositionUiModel
import com.harunbekcan.androidassessment.domain.model.SatelliteDetailUiModel
import com.harunbekcan.androidassessment.domain.usecase.satellitedetail.SatelliteDetailUseCase
import com.harunbekcan.androidassessment.utils.Constants.SATELLITE_ID
import com.harunbekcan.androidassessment.utils.Constants.SATELLITE_NAME
import com.harunbekcan.androidassessment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    private val satelliteDetailUseCase: SatelliteDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _satelliteDetailState = MutableStateFlow(SatelliteDetailState())
    val satelliteDetailState: StateFlow<SatelliteDetailState> = _satelliteDetailState

    init {
        savedStateHandle.get<Int>(SATELLITE_ID)?.let {
            getSatelliteDetail(it)
        }
        savedStateHandle.get<String>(SATELLITE_NAME)?.let {
            _satelliteDetailState.value = _satelliteDetailState.value.copy(
                satelliteName = it
            )
        }
    }

    private fun getSatelliteDetail(satelliteId: Int) {
        satelliteDetailUseCase(satelliteId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data.let {
                        _satelliteDetailState.value = _satelliteDetailState.value.copy(
                            satelliteDetailUiModel = it?.first ?: SatelliteDetailUiModel(),
                            satellitePositionUIModel = it?.second ?: PositionUiModel(),
                            isLoading = false
                        )
                    }
                }

                is Resource.Error -> {
                    _satelliteDetailState.value = _satelliteDetailState.value.copy(
                        isLoading = false,
                        errorMessage = result.errorMessage.orEmpty()
                    )
                }

                is Resource.Loading -> {
                    _satelliteDetailState.value = _satelliteDetailState.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}