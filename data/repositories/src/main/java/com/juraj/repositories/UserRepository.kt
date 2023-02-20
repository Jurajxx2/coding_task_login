package com.juraj.repositories

interface UserRepository {
    suspend fun createAccount(email: String, password: String)
    fun isUserLoggedIn(): Boolean
    fun logout()
}