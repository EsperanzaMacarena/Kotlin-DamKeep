package com.salesianostrianafct.damkeep.repository

import com.salesianostrianafct.damkeep.model.MyUser
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<MyUser, UUID> {

    fun findByUsername(username: String): Optional<MyUser>


}