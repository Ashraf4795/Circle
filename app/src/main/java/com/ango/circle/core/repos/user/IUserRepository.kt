package com.ango.circle.core.repos.user

import com.ango.circle.core.state.State

interface IUserRepository {

    suspend fun createUser(email:String, password:String, onCompleteListener:(State)->Unit)
    suspend fun signInUser(email: String,password: String,onCompleteListener: (State) -> Unit)
    suspend fun signOutUser()

}