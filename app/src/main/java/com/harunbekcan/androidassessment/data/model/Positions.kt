package com.harunbekcan.androidassessment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "positions")
data class Positions(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val positions: List<Position?>?
)