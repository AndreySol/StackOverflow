package com.example.stackoverflow.auth.core

interface EmailValidationService {
    fun isValid(email: String): Boolean
}