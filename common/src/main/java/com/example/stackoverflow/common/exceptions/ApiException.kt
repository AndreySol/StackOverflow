package com.example.stackoverflow.common.exceptions

class ApiException(
    val errorCode: Int,
    val errorMsg: String
) : Exception()