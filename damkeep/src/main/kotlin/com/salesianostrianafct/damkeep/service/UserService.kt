package com.salesianostrianafct.damkeep.service

import com.salesianostrianafct.damkeep.dto.UserSignUp
import com.salesianostrianafct.damkeep.dto.toMyUser
import com.salesianostrianafct.damkeep.model.MyUser
import com.salesianostrianafct.damkeep.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val encoder: PasswordEncoder) : BaseService<MyUser, UUID, UserRepository>() {
    fun findByUsername(username: String) = this.repository.findByUsername(username)

    fun create(user: UserSignUp): Optional<MyUser> {
        if (findByUsername(user.username).isPresent) return Optional.empty()
        return Optional.of(
                this.save(user.toMyUser())
        )
    }
}