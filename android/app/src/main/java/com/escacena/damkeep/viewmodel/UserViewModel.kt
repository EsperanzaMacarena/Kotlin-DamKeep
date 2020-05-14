package com.escacena.damkeep.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.escacena.damkeep.api.request.LoginRequest
import com.escacena.damkeep.model.User
import com.escacena.damkeep.repository.DamKeepRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(damKeepRepository: DamKeepRepository) : ViewModel() {
    val repository = damKeepRepository

    fun doLogin(request: LoginRequest): LiveData<User> {
        return repository.login(request)
    }

    fun signup(request: LoginRequest):LiveData<User>{
        return repository.register(request)
    }
}