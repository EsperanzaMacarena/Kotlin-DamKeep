package com.escacena.damkeep.api.response

import com.escacena.damkeep.model.User

data class LoginResponse (
    val token :String,
    val user : User
)