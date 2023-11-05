package com.harunbekcan.androidassessment.di

import com.harunbekcan.androidassessment.data.repository.SatellitesRepositoryImpl
import com.harunbekcan.androidassessment.domain.repository.SatellitesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindsSatellitesRepository(satellitesRepositoryImpl: SatellitesRepositoryImpl): SatellitesRepository
}