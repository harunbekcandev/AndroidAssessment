package com.harunbekcan.androidassessment.data.source.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.harunbekcan.androidassessment.data.model.Position
import javax.inject.Inject

@ProvidedTypeConverter
class SatellitesTypeConverters @Inject constructor(private val gson: Gson) {

    @TypeConverter
    fun fromString(value: String?): List<Position?> {
        val listType = object : TypeToken<ArrayList<Position?>?>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Position?>?): String {
        return gson.toJson(list)
    }
}