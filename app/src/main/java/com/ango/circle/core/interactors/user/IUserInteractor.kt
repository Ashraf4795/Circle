package com.ango.circle.core.interactors.user

import com.ango.circle.core.state.State

interface IUserInteractor {
    fun createUser(email: String, password: String, onCompleteListener: (State) -> Unit)
    fun signInUser(email: String,password: String,onCompleteListener: (State) -> Unit)
    fun signOutUser()
}