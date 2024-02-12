package com.example.stackoverflow.auth.core

import android.util.Patterns
import javax.inject.Inject

class EmailValidationServiceImpl @Inject constructor() : EmailValidationService {
    override fun isValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}