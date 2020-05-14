package com.escacena.damkeep.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.escacena.damkeep.api.DamKeepService
import com.escacena.damkeep.api.request.LoginRequest
import com.escacena.damkeep.api.request.NoteCreateRequest
import com.escacena.damkeep.api.response.LoginResponse
import com.escacena.damkeep.common.Constants
import com.escacena.damkeep.common.MyApp
import com.escacena.damkeep.common.MySharedPreferencesManager
import com.escacena.damkeep.model.Note
import com.escacena.damkeep.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DamKeepRepository @Inject constructor(var damKeepService: DamKeepService) {
    var user: MutableLiveData<User> = MutableLiveData()
    var newUser: MutableLiveData<User> = MutableLiveData()
    var notes: MutableLiveData<List<Note>> = MutableLiveData()

    var note: MutableLiveData<Note> = MutableLiveData()


    fun login(request: LoginRequest): MutableLiveData<User> {
        val call: Call<LoginResponse>? = damKeepService.login(request)
        call?.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    user.value = response.body()?.user!!
                    MySharedPreferencesManager().setStringValue(
                        Constants.SHARED_PREFERENCES_TOKEN_KEYWORD,
                        response.body()!!.token
                    )
                    Log.d(
                        "TOKEN",
                        MySharedPreferencesManager().getSharedPreferences().getString(
                            Constants.SHARED_PREFERENCES_TOKEN_KEYWORD,
                            "null"
                        )
                    )
                }else{
                    user.postValue(null)
                    MySharedPreferencesManager().removeStringValue(Constants.SHARED_PREFERENCES_TOKEN_KEYWORD)
                    Toast.makeText(MyApp.instance, "El usuario o contraseña no es correcto", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, t.message, Toast.LENGTH_SHORT).show()

            }
        })
        return user
    }

    fun register(request: LoginRequest): MutableLiveData<User> {
        val call: Call<User>? = damKeepService.signup(request)
        call?.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful)
                    newUser.value = response.body()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(MyApp.instance, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return newUser
    }

    fun getAllNotes(): MutableLiveData<List<Note>> {
        val call: Call<List<Note>>? = damKeepService.getNotesByUser()

        call?.enqueue(object : Callback<List<Note>> {
            override fun onResponse(call: Call<List<Note>>, response: Response<List<Note>>) {
                if (response.isSuccessful) notes.value = response.body()
            }

            override fun onFailure(call: Call<List<Note>>, t: Throwable) {
                Toast.makeText(MyApp.instance, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return notes
    }

    fun getNote(id: String): MutableLiveData<Note> {
        val call: Call<Note>? = damKeepService.getNote(id)

        call?.enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                if (response.isSuccessful) note.value = response.body()
            }

            override fun onFailure(call: Call<Note>, t: Throwable) {
                Toast.makeText(MyApp.instance, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return note
    }

    fun removeNote(id: String){
        val call: Call<Void>?= damKeepService.deleteNote(id)
        call?.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful)
                    Toast.makeText(MyApp.instance, "Se ha eliminado correctamente la nota", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(MyApp.instance, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun modifyNote(id: String, request: NoteCreateRequest): MutableLiveData<Note> {
        val call: Call<Note>? = damKeepService.editNote(id,request)

        call?.enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                if (response.isSuccessful) note.value = response.body()
            }

            override fun onFailure(call: Call<Note>, t: Throwable) {
                Toast.makeText(MyApp.instance, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return note
    }

    fun newNote(request: NoteCreateRequest): MutableLiveData<Note> {
        val call: Call<Note>? = damKeepService.createNote(request)

        call?.enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                if (response.isSuccessful) note.value = response.body()
            }

            override fun onFailure(call: Call<Note>, t: Throwable) {
                Toast.makeText(MyApp.instance, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return note
    }
}