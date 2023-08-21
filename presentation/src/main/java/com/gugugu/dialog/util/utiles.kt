package com.gugugu.dialog.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter


internal fun String.toTime(): String =
    when(this) {
        "breakfast" -> "아침"
        "launch" -> "점심"
        "dinner" -> "저녁"
        else -> "알 수 없음"
    }
fun getDate(): String {
    val pattern = "yyyyMMdd"

    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern(pattern)

    return currentDate.format(formatter)
}