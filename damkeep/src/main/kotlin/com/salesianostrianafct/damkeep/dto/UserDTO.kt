package com.salesianostrianafct.damkeep.dto

import com.salesianostrianafct.damkeep.model.MyUser
import java.util.*
import javax.persistence.Column
import javax.validation.constraints.NotEmpty
import kotlin.collections.ArrayList

data class UserShow(
        val id: UUID?,
        var username: String,
        var rol: String
)

data class UserSignUp(
        var username: String,
        var password: String
)

fun MyUser.toUserShow() = UserShow(id, username, if (roles.size == 1) roles.first() else roles.elementAt(1))

fun UserSignUp.toMyUser() = MyUser(username, password, ArrayList(), "USER")