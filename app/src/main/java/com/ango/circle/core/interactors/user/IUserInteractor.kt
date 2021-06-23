package com.ango.circle.core.interactors.user

import com.ango.circle.core.state.State

interface IUserInteractor {
    suspend fun createUser(email: String, password: String, onCompleteListener: (State) -> Unit)
    suspend fun signInUser(email: String,password: String,onCompleteListener: (State) -> Unit)
    suspend fun signOutUser()
}