package com.escacena.damkeep.api.response

import java.util.*

data class SignupResponse(
    val id: UUID,
    val username: String,
    val rol: String
)