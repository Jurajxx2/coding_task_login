package com.juraj.api

import com.juraj.api.model.CreateAccountResponse

interface UserService {
    suspend fun createAccount(email: String, password: String): String
}