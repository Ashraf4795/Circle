package com.ango.circle.core.interactors.user

import com.ango.circle.core.state.ErrorState
import com.ango.circle.core.state.State
import com.ango.circle.core.state.SuccessState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*

class UserInteractorImpl(
    private val firebaseAuth: FirebaseAuth,
    private val IO: CoroutineDispatcher = Dispatchers.IO
) : IUserInteractor {

    override suspend fun createUser(
        email: String,
        password: String,
        onCompleteListener: (State) -> Unit
    ) {
        withContext(IO) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    onCompleteListener(SuccessState("Successful"))
                } else if (it.isCanceled) {
                    onCompleteListener(ErrorState<String>(message = "SignUp is cancelled!"))
                } else {
                    onCompleteListener(ErrorState<String>(message = it.exception?.message))
                }
            }
        }
    }

    override suspend fun signInUser(
        email: String,
        password: String,
        onCompleteListener: (State) -> Unit
    ) {
        withContext(IO) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    onCompleteListener(SuccessState("Welcome.."))
                } else if (it.isCanceled) {
                    onCompleteListener(ErrorState<String>(message = "SignIn is cancelled!"))
                } else {
                    onCompleteListener(ErrorState<String>(message = it.exception?.message))
                }
            }
        }
    }

    override suspend fun signOutUser() {
        withContext(IO) {
            firebaseAuth.signOut()
        }
    }

}