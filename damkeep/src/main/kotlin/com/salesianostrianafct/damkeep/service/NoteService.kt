package com.salesianostrianafct.damkeep.service

import com.salesianostrianafct.damkeep.model.MyUser
import com.salesianostrianafct.damkeep.model.Note
import com.salesianostrianafct.damkeep.repository.NoteRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class NoteService : BaseService<Note, UUID, NoteRepository>() {

    fun findByUser(user :MyUser):List<Note>{
        return this.repository.findAllByAuthor(user)
    }
}