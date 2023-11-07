package com.harunbekcan.androidassessment.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.harunbekcan.androidassessment.data.source.converter.SatellitesTypeConverters
import com.harunbekcan.androidassessment.data.source.local.SatellitesDao
import com.harunbekcan.androidassessment.data.source.local.SatellitesDatabase
import com.harunbekcan.androidassessment.utils.getJsonDataFromAsset
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @SatelliteListJson
    fun provideSatelliteListJson(
        @ApplicationContext context: Context,
    ): String {
        return getJsonDataFromAsset(context, "satellite-list.json").orEmpty()
    }

    @Provides
    @SatelliteDetailJson
    fun provideSatelliteDetailJson(
        @ApplicationContext context: Context,
    ): String {
        return getJsonDataFromAsset(context, "satellite-detail.json").orEmpty()
    }

    @Provides
    @SatellitePositionsJson
    fun provideSatellitePositionsJson(
        @ApplicationContext context: Context,
    ): String {
        return getJsonDataFromAsset(context, "positions.json").orEmpty()
    }

    @Provides
    @Singleton
    fun provideSatelliteDao(satellitesDatabase: SatellitesDatabase): SatellitesDao = satellitesDatabase.getSatelliteDao()

    @Provides
    @Singleton
    fun provideSatellitesDatabase(
        @ApplicationContext context: Context,
        typeConverters: SatellitesTypeConverters
    ): SatellitesDatabase = Room
        .databaseBuilder(context, SatellitesDatabase::class.java, "satellites_app.db")
        .addTypeConverter(typeConverters)
        .build()
}