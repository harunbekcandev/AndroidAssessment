package com.harunbekcan.androidassessment.di

import com.google.gson.Gson
import com.harunbekcan.androidassessment.data.repository.SatellitesRepositoryImpl
import com.harunbekcan.androidassessment.data.source.local.SatellitesDao
import com.harunbekcan.androidassessment.domain.repository.SatellitesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun bindsSatellitesRepository(
        @SatelliteListJson getSatelliteList: String,
        @SatelliteDetailJson getSatelliteDetailJson: String,
        @SatellitePositionsJson getSatellitePositions: String,
        satellitesDao: SatellitesDao,
        gson:Gson
    ): SatellitesRepository = SatellitesRepositoryImpl(
        getSatelliteList,
        getSatelliteDetailJson,
        getSatellitePositions,
        satellitesDao,
        gson
    )
}