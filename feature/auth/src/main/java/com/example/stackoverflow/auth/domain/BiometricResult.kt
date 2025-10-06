package com.example.stackoverflow.auth.domain

sealed class BiometricResult {
    object Success : BiometricResult()
    object Failed : BiometricResult()
    object Cancelled : BiometricResult()
    object NotAvailable : BiometricResult()
    object NotEnrolled : BiometricResult()
    object ErrorLockout : BiometricResult()
    object ErrorLockoutPermanent : BiometricResult()
    data class Error(val errorCode: Int, val errorMessage: String) : BiometricResult()
}