package com.juraj.api.impl

import com.juraj.api.UserService
import com.juraj.api.client.CodingTaskHttpClient
import com.juraj.api.client.Endpoints
import com.juraj.api.model.CreateAccountRequest
import com.juraj.api.model.CreateAccountResponse
import com.juraj.common.utils.NetworkException
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import io.ktor.client.utils.*
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

class UserServiceImpl @Inject constructor(val client: CodingTaskHttpClient) : UserService {
    override suspend fun createAccount(email: String, password: String): String {
        if (!email.endsWith("@monstarlab.com")) {
            throw NetworkException(403, "User not found")
        }
        delay(2000)
        return "token123"
        //TODO add server
        /*return client.post<CreateAccountResponse>(
            Endpoints.CREATE_ACCOUNT,
            CreateAccountRequest(email, password)
        ).token*/
    }
}