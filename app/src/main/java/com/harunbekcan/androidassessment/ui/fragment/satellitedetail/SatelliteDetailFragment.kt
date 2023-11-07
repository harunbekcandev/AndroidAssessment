package com.harunbekcan.androidassessment.ui.fragment.satellitedetail

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.harunbekcan.androidassessment.R
import com.harunbekcan.androidassessment.base.BaseFragment
import com.harunbekcan.androidassessment.databinding.FragmentSatelliteDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteDetailFragment : BaseFragment<FragmentSatelliteDetailBinding>(FragmentSatelliteDetailBinding::inflate) {

    private val viewModel : SatelliteDetailViewModel by viewModels()
    override fun prepareView(savedInstanceState: Bundle?) {
        initObserver()
    }

    private fun checkProgressBar(isLoading: Boolean) = with(binding) {
        progressBar.isVisible = isLoading
    }

    private fun initUi(state: SatelliteDetailState) {
        with(binding) {

            val uiModel = state.satelliteDetailUiModel
            val positionsUiModel = state.satellitePositionUIModel

            satelliteNameTextView.text = state.satelliteName
            dateTextView.text = uiModel.firstFlight
            costTextView.text = getString(R.string.cost_text, uiModel.costPerLaunch)
            heightMassTextView.text = getString(R.string.height_mass_text, uiModel.height, uiModel.mass)
            lastPositionTextView.text = getString(R.string.last_position_text, positionsUiModel.posX, positionsUiModel.posY)
        }
    }
    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.satelliteDetailState.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collect {
                checkProgressBar(it.isLoading)
                initUi(it)
            }
        }
    }
}