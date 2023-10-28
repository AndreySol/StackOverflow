package com.example.stackoverflow.common

import java.util.logging.Logger

object AppLogger {
    const val TAG = "StackOverflow.logging.tag"
    val logger = Logger.getLogger(TAG)

    fun info(msg: String) {
        logger.info(msg)
    }

    fun error(e: Exception) {
        logger.warning("ERROR: ${e.toString()}")
        e.printStackTrace()
    }

    fun error(msg: String) {
        logger.warning("ERROR: $msg")
    }
}