package com.juraj.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateAccountRequest(
    @SerialName("user_email") val email: String,
    @SerialName("user_password") val password: String,
)