package com.salesianostrianafct.damkeep.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.salesianostrianafct.damkeep.model.MyUser
import com.salesianostrianafct.damkeep.model.Note
import java.time.LocalDateTime
import java.util.*


data class NoteShow(
        val id: UUID? = null,
        var title: String,
        var body: String,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        var createdOn: LocalDateTime,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        var editedOn: LocalDateTime,

        var author: UserShow? = null
)

data class CreateNote(
        var title: String,
        var body: String
)

fun Note.toNoteShow() = NoteShow(id, title, body,
        author = author?.toUserShow(), createdOn = this.createdOn!!, editedOn = this.editedOn!!)

fun CreateNote.toNote(user: MyUser) = Note(title, body, author = user)
