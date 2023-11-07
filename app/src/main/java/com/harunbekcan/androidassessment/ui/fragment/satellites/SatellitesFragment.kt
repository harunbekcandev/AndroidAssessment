package com.harunbekcan.androidassessment.ui.fragment.satellites

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.harunbekcan.androidassessment.base.BaseFragment
import com.harunbekcan.androidassessment.data.model.Satellite
import com.harunbekcan.androidassessment.databinding.FragmentSatellitesBinding
import com.harunbekcan.androidassessment.ui.adapter.SatellitesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatellitesFragment : BaseFragment<FragmentSatellitesBinding>(FragmentSatellitesBinding::inflate) {

    private val viewModel : SatellitesViewModel by viewModels()
    private val satellitesAdapter by lazy { SatellitesAdapter() }
    override fun prepareView(savedInstanceState: Bundle?) {
        initObserver()
        initSearchView()
        itemClickListener()
    }

    private fun initAdapter(list: List<Satellite>) {
        satellitesAdapter.submitList(list)
        binding.satellitesRecyclerView.adapter = satellitesAdapter
    }

    private fun itemClickListener(){
        satellitesAdapter.satelliteItemClick = { item->
            findNavController().navigate(SatellitesFragmentDirections.actionSatellitesFragmentToSatelliteDetailFragment(item.id,item.name))
        }
    }

    private fun checkProgressBar(isLoading: Boolean) = with(binding) {
        progressBar.isVisible = isLoading
        satellitesRecyclerView.isVisible = isLoading.not()
    }

    private fun initSearchView(){
        binding.searchLayout.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(searchKey: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchKey: String?): Boolean {
                searchKey?.let { viewModel.searchSatelliteList(it) }
                return false
            }
        })
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.satellitesState.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collect {
                checkProgressBar(it.isLoading)
                initAdapter(it.data)
            }
        }
    }
}