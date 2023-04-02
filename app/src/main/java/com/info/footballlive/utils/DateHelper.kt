package com.info.footballlive.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun getStringDate(date: String): String? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val output = SimpleDateFormat("EEE, dd MMM yyy")
    var t: Date? = null
    try {
        t = dateFormat.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return output.format(t)
}