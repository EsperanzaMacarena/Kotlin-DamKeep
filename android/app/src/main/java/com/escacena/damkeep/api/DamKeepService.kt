package com.escacena.damkeep.api

import com.escacena.damkeep.api.request.LoginRequest
import com.escacena.damkeep.api.request.NoteCreateRequest
import com.escacena.damkeep.api.response.LoginResponse
import com.escacena.damkeep.model.Note
import com.escacena.damkeep.model.User
import retrofit2.Call
import retrofit2.http.*

interface DamKeepService {
    @POST("/auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("/notes/")
    fun getNotesByUser(): Call<List<Note>>

    @GET("/notes/{id}")
    fun getNote(@Path("id") id: String): Call<Note>

    @DELETE("/notes/{id}")
    fun deleteNote(@Path("id") id: String): Call<Void>

    @PUT("/notes/{id}")
    fun editNote(@Path("id") id: String, @Body request: NoteCreateRequest): Call<Note>

    @POST("/notes/")
    fun createNote(@Body request: NoteCreateRequest): Call<Note>

    @POST("/signup")
    fun signup(@Body request:LoginRequest): Call<User>
}