package com.harunbekcan.androidassessment.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.harunbekcan.androidassessment.data.model.Positions
import com.harunbekcan.androidassessment.data.model.SatelliteDetail
import com.harunbekcan.androidassessment.data.source.converter.SatellitesTypeConverters

@Database(entities = [SatelliteDetail::class, Positions::class], version = 1, exportSchema = false)
@TypeConverters(SatellitesTypeConverters::class)
abstract class SatellitesDatabase : RoomDatabase() {
    abstract fun getSatelliteDao(): SatellitesDao
}