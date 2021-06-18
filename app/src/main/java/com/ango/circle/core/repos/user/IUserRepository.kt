package com.ango.circle.core.repos.user

import com.ango.circle.core.state.State

interface IUserRepository {

    fun createUser(email:String, password:String, onCompleteListener:(State)->Unit)
    fun signInUser(email: String,password: String,onCompleteListener: (State) -> Unit)
    fun signOutUser()

}