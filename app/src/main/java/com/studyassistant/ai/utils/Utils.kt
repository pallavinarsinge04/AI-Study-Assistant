package com.studyassistant.ai.utils

import kotlin.math.roundToInt

/**
 * Result sealed class for handling API responses
 */
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

/**
 * Utility functions for common operations
 */
fun <T> Result<T>.getOrNull(): T? = when (this) {
    is Result.Success -> data
    else -> null
}

fun <T> Result<T>.isSuccess(): Boolean = this is Result.Success

fun <T> Result<T>.isError(): Boolean = this is Result.Error

/**
 * Date and time utility functions
 */
object DateTimeUtils {
    
    fun getCurrentTimestamp(): Long = System.currentTimeMillis()
    
    fun getDaysDifference(from: Long, to: Long): Int {
        return ((to - from) / (1000 * 60 * 60 * 24)).toInt()
    }
    
    fun getHoursDifference(from: Long, to: Long): Int {
        return ((to - from) / (1000 * 60 * 60)).toInt()
    }
    
    fun getMinutesDifference(from: Long, to: Long): Int {
        return ((to - from) / (1000 * 60)).toInt()
    }
    
    fun getSecondsDifference(from: Long, to: Long): Int {
        return ((to - from) / 1000).toInt()
    }
    
    fun formatDurationInHours(seconds: Int): Float {
        return (seconds / 3600f).roundToInt().toFloat()
    }
    
    fun formatDurationInMinutes(seconds: Int): Int {
        return seconds / 60
    }
    
    fun addDays(timestamp: Long, days: Int): Long {
        return timestamp + (days * 24 * 60 * 60 * 1000)
    }
    
    fun addHours(timestamp: Long, hours: Int): Long {
        return timestamp + (hours * 60 * 60 * 1000)
    }
    
    fun addMinutes(timestamp: Long, minutes: Int): Long {
        return timestamp + (minutes * 60 * 1000)
    }
    
    fun isToday(timestamp: Long): Boolean {
        val today = System.currentTimeMillis()
        val diff = getDaysDifference(timestamp, today)
        return diff == 0
    }
    
    fun isYesterday(timestamp: Long): Boolean {
        val today = System.currentTimeMillis()
        val diff = getDaysDifference(timestamp, today)
        return diff == 1
    }
}