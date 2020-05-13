package com.salesianostrianafct.damkeep.controller

import com.salesianostrianafct.damkeep.dto.UserShow
import com.salesianostrianafct.damkeep.dto.UserSignUp
import com.salesianostrianafct.damkeep.dto.toMyUser
import com.salesianostrianafct.damkeep.dto.toUserShow
import com.salesianostrianafct.damkeep.model.MyUser
import com.salesianostrianafct.damkeep.security.JwtTokenProvider
import com.salesianostrianafct.damkeep.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
class AuthenticationController(
        private val authenticationManager: AuthenticationManager,
        private val jwtTokenProvider: JwtTokenProvider,
        private val userService: UserService,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    @PostMapping("/auth/login")
    fun login(@Valid @RequestBody loginRequest: LoginRequest): JwtUserResponse {
        val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        loginRequest.username, loginRequest.password
                )
        )

        SecurityContextHolder.getContext().authentication = authentication

        val user = authentication.principal as MyUser
        val jwtToken = jwtTokenProvider.generateToken(authentication)

        return JwtUserResponse(jwtToken, user.toUserShow())

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/me")
    fun me(@AuthenticationPrincipal user: MyUser) = user.toUserShow()

    @PostMapping("/signup")
    fun signup(@RequestBody user: UserSignUp): UserShow{
        val result = userService.findByUsername(user.username)
        if(result==null) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario ${user.username} ya existe. Pruebe otro.")
        else {
            user.password= bCryptPasswordEncoder.encode(user.password)
            return userService.save(user.toMyUser()).toUserShow()
        }
    }
}


data class LoginRequest(
        @NotBlank val username: String,
        @NotBlank val password: String
)

data class JwtUserResponse(
        val token: String,
        val user: UserShow
)