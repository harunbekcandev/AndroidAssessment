package com.harunbekcan.androidassessment.utils

fun <T> List<T>?.orEmptyList() = this ?: emptyList()
fun Int?.orZero() = this ?: 0
fun Long?.orZero() = this ?: 0
fun Double?.orZero() = this ?: 0.0