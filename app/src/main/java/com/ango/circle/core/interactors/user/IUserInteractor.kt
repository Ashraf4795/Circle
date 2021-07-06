package com.ango.circle.core.interactors.user

import com.ango.circle.core.data.model.User
import com.ango.circle.core.state.State

interface IUserInteractor {
    suspend fun signUpUser(email: String, password: String, onCompleteListener: (State) -> Unit)
    suspend fun signInUser(email: String,password: String,onCompleteListener: (State) -> Unit)
    suspend fun signOutUser()

    suspend fun insertUser(user: User,onCompleteListener:(State)->Unit)
    suspend fun getCategories(onCompleteListener:(State)->Unit)

}