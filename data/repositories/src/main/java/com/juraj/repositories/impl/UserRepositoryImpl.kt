package com.juraj.repositories.impl

import com.juraj.api.UserService
import com.juraj.common.utils.EncryptedPrefs
import com.juraj.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val userService: UserService): UserRepository {
    override suspend fun createAccount(email: String, password: String) {
        val resultToken = userService.createAccount(email, password)
        EncryptedPrefs.setAuthorizationHeader(resultToken)
    }

    override fun logout() {
        EncryptedPrefs.clearAuthorizationHeader()
    }

    override fun isUserLoggedIn(): Boolean {
        return !EncryptedPrefs.authorizationHeader.isNullOrEmpty()
    }
}