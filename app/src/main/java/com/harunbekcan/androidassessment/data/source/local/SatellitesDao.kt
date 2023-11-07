package com.harunbekcan.androidassessment.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.harunbekcan.androidassessment.data.model.Positions
import com.harunbekcan.androidassessment.data.model.SatelliteDetail

@Dao
interface SatellitesDao {

    @Query("SELECT * FROM satellite_detail WHERE id = :satelliteId")
    suspend fun getSatelliteDetails(satelliteId: Int): SatelliteDetail?

    @Query("SELECT * FROM positions WHERE id = :satelliteId")
    suspend fun getSatellitePositions(satelliteId: Int): Positions?

    @Insert
    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetail)

    @Insert
    suspend fun insertSatellitePosition(positions: Positions)
}