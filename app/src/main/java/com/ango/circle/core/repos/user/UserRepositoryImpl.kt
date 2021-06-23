package com.ango.circle.core.repos.user

import com.ango.circle.core.interactors.user.IUserInteractor
import com.ango.circle.core.state.State

class UserRepositoryImpl (private val userInteractorImpl: IUserInteractor) : IUserRepository {

    override suspend fun createUser(email: String, password: String, onCompleteListener: (State) -> Unit) {
        userInteractorImpl.createUser(email, password, onCompleteListener)
    }

    override suspend fun signInUser(email: String, password: String, onCompleteListener: (State) -> Unit) {
        userInteractorImpl.signInUser(email,password,onCompleteListener)
    }

    override suspend fun signOutUser() {
        userInteractorImpl.signOutUser()
    }

}