package com.ango.circle.core.repos.user

import com.ango.circle.core.data.model.User
import com.ango.circle.core.interactors.user.IUserInteractor
import com.ango.circle.core.state.State

class UserRepositoryImpl (private val userInteractorImpl: IUserInteractor) : IUserRepository {

    override suspend fun signupUser(email: String, password: String, onCompleteListener: (State) -> Unit) {
        userInteractorImpl.signUpUser(email, password, onCompleteListener)
    }

    override suspend fun signInUser(email: String, password: String, onCompleteListener: (State) -> Unit) {
        userInteractorImpl.signInUser(email,password,onCompleteListener)
    }

    override suspend fun signOutUser() {
        userInteractorImpl.signOutUser()
    }

    override suspend fun insertUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun getCategories(onCompleteListener: (State) -> Unit) {
        userInteractorImpl.getCategories(onCompleteListener)
    }

}