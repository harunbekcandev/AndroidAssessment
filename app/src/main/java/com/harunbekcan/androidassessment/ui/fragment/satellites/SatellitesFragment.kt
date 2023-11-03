package com.harunbekcan.androidassessment.ui.fragment.satellites

import android.os.Bundle
import com.harunbekcan.androidassessment.base.BaseFragment
import com.harunbekcan.androidassessment.databinding.FragmentSatellitesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SatellitesFragment : BaseFragment<FragmentSatellitesBinding>(FragmentSatellitesBinding::inflate) {
    override fun prepareView(savedInstanceState: Bundle?) {}
}