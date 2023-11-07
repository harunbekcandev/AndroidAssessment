package com.harunbekcan.androidassessment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.harunbekcan.androidassessment.utils.Constants

@Entity(tableName = "satellite_detail")
data class SatelliteDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @SerializedName(Constants.COST_PER_LAUNCH)
    val costPerLaunch: Long?,
    @SerializedName(Constants.FIRST_FLIGHT)
    val firstFlight: String?,
    val height: Int?,
    val mass: Long?
)