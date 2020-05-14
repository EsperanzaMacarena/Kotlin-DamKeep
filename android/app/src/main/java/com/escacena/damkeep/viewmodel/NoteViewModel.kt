package com.escacena.damkeep.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.escacena.damkeep.api.request.NoteCreateRequest
import com.escacena.damkeep.model.Note
import com.escacena.damkeep.repository.DamKeepRepository
import javax.inject.Inject

class NoteViewModel @Inject constructor(damKeepRepository: DamKeepRepository) : ViewModel()  {
    val repository = damKeepRepository

    fun getAll():LiveData<List<Note>>{
        return repository.getAllNotes()
    }

    fun getNote(id:String):LiveData<Note>{
        return repository.getNote(id)
    }

    fun removeNote(id: String){
        return repository.removeNote(id)
    }

    fun editNote(id:String, request: NoteCreateRequest):LiveData<Note>{
        return repository.modifyNote(id, request)
    }

    fun createNote(request: NoteCreateRequest):LiveData<Note>{
        return repository.newNote(request)
    }
}