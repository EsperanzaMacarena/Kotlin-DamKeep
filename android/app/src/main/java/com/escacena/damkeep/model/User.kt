package com.escacena.damkeep.model

import java.util.*

data class User(
    val id : UUID,
    val username : String,
    val rol: String
)