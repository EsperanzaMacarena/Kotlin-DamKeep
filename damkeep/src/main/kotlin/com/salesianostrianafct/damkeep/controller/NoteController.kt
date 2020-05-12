package com.salesianostrianafct.damkeep.controller

import com.salesianostrianafct.damkeep.dto.CreateNote
import com.salesianostrianafct.damkeep.dto.NoteShow
import com.salesianostrianafct.damkeep.dto.toNoteShow
import com.salesianostrianafct.damkeep.model.MyUser
import com.salesianostrianafct.damkeep.model.Note
import com.salesianostrianafct.damkeep.service.NoteService
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/notes")
class NoteController(val noteService: NoteService) {

    @GetMapping("/{id}")
    fun getNote(@PathVariable id: UUID): NoteShow {
        var result = noteService.findById(id)

        if (result.isPresent) return result.get().toNoteShow()
        else throw ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado la nota con el identificador $id")
    }

    @PutMapping("/{id}")
    fun editNote(@PathVariable id: UUID, @RequestBody note: CreateNote): NoteShow {
        return noteService.findById(id)
                .map { x ->
                    val edited: Note =
                            x.copy(title = note.title, body = note.body)
                    noteService.save(edited).toNoteShow()
                }.orElseThrow {
                    ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado la nota con el identificador $id")
                }
    }

    @DeleteMapping("/{id}")
    fun deleteNote(@PathVariable id: UUID): ResponseEntity<Void> {
        var result = noteService.findById(id)
        if (result.isPresent) {
            noteService.deleteById(id)
            return ResponseEntity.noContent().build()
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado la nota con el identificador $id")


    }
/*
    @PostMapping("/")
    fun postNote(@PathVariable id: UUID): Note {
        val result = noteService.findById(id)
        return result.orElseThrow {
            ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No se ha encontrado la nota con el identificador $id")
        }
    }

    @GetMapping("/")
    fun getNotesByUser(@AuthenticationPrincipal user: MyUser): List<Note> {
        val result: List<Note> = noteService.findByUser(user)
        if (result.isNotEmpty()) return result
        else throw ResponseStatusException(HttpStatus.NO_CONTENT, "No hay notas que mostrar")
    }
*/

}