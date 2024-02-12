package com.example.stackoverflow.auth.data

import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

class RemoteDataSource @Inject constructor() {
    suspend fun signIn(email: String, password: String): Boolean {
        delay(2000)
        // return random result
        return Random.nextBoolean()
    }
}