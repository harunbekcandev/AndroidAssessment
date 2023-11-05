package com.harunbekcan.androidassessment.utils

import android.view.View

fun View.setVisibilityIfLastItem(isLastItem: Boolean) {
    visibility = if (isLastItem) View.GONE else View.VISIBLE
}