package com.escacena.damkeep.model

import java.util.*

data class Note(
    val id: UUID,
    val title: String,
    val body: String,
    val createdDate: String,
    val editedOn: String,
    val author: User
)