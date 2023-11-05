package com.harunbekcan.androidassessment.ui.fragment.satellites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harunbekcan.androidassessment.domain.usecase.SatellitesUseCase
import com.harunbekcan.androidassessment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SatellitesViewModel @Inject constructor(private val satellitesUseCase: SatellitesUseCase) : ViewModel() {

    private val _satellitesState = MutableStateFlow(SatellitesState())
    val satellitesState: StateFlow<SatellitesState> = _satellitesState

    init {
        getSatellites()
    }

    private fun getSatellites() {
        satellitesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data.let {
                        _satellitesState.value = _satellitesState.value.copy(
                            data = it,
                            isSuccess = true,
                            isError = false,
                            isLoading = false
                        )
                    }
                }

                is Resource.Error -> {
                    _satellitesState.value = _satellitesState.value.copy(
                        isError = true,
                        isSuccess = false,
                        isLoading = false,
                        errorMessage = result.errorMessage.orEmpty()
                    )
                }

                is Resource.Loading -> {
                    _satellitesState.value = _satellitesState.value.copy(
                        isLoading = true,
                        isError = false,
                        isSuccess = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}