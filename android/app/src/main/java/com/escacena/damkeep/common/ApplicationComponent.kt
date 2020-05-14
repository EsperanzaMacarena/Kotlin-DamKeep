package com.escacena.damkeep.common

import com.escacena.damkeep.*
import com.escacena.damkeep.api.DamKeepClient
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DamKeepClient::class])
interface ApplicationComponent {
    fun inject(main: MainActivity)
    fun inject(noteFragment: NoteFragment)
    fun inject(noteDetailActivity: NoteDetailActivity)
    fun inject(createEditNoteActivity: CreateEditNoteActivity)
    fun inject(registerActivity: RegisterActivity)
}