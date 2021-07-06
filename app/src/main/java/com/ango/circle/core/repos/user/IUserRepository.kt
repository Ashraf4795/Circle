package com.ango.circle.core.repos.user

import com.ango.circle.core.data.model.User
import com.ango.circle.core.state.State

interface IUserRepository {

    suspend fun signupUser(email:String, password:String, onCompleteListener:(State)->Unit)
    suspend fun signInUser(email: String,password: String,onCompleteListener: (State) -> Unit)
    suspend fun signOutUser()

    suspend fun insertUser(user: User,onCompleteListener:(State)->Unit)
    suspend fun getCategories(onCompleteListener:(State)->Unit)
}