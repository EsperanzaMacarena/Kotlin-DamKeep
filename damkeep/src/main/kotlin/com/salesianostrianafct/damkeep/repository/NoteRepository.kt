package com.salesianostrianafct.damkeep.repository

import com.salesianostrianafct.damkeep.model.MyUser
import com.salesianostrianafct.damkeep.model.Note
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface NoteRepository : JpaRepository<Note, UUID> {

    fun findAllByAuthor(author: MyUser): List<Note>
}