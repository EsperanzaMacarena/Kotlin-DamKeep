package com.salesianostrianafct.damkeep.dummy

import com.salesianostrianafct.damkeep.model.MyUser
import com.salesianostrianafct.damkeep.model.Note
import com.salesianostrianafct.damkeep.repository.NoteRepository
import com.salesianostrianafct.damkeep.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class Data(
        val userRepository: UserRepository,
        val noteRepository: NoteRepository,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    @PostConstruct
    fun initData() {
        val users = listOf(
                MyUser("usuario", bCryptPasswordEncoder.encode("usuario"), ArrayList(), "USER"),
                MyUser("admin", bCryptPasswordEncoder.encode("admin"), ArrayList(), "ADMIN")
        )
        userRepository.saveAll(users)

        val notes = listOf(
                Note("Mi primera nota", "Hello World 1", users.get(0)),
                Note("Mi segunda nota", "Hacer el ejercicio del palíndromo", users.get(0)),
                Note("Mi primera nota", "Administrar DamKeep", users.get(1)),
                Note("Mi tecera nota", "Aplaudir a las 20:00h", users.get(0)),
                Note("Mi cuarta nota", "Cacerolada a las 19:30h", users.get(0)),
                Note("Mi quinta nota", "Muerte a PL/SQL", users.get(0))
        )
        noteRepository.saveAll(notes)
    }

}