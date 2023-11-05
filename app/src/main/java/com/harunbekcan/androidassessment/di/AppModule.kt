package com.harunbekcan.androidassessment.di

import android.content.Context
import com.harunbekcan.androidassessment.utils.getJsonDataFromAsset
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @SatelliteListJson
    fun provideSatelliteListJson(
        @ApplicationContext context: Context,
    ): String {
        return getJsonDataFromAsset(context, "satellite-list.json").orEmpty()
    }
}