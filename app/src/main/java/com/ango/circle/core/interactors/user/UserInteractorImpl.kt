package com.ango.circle.core.interactors.user

import com.ango.circle.core.categoryCollection
import com.ango.circle.core.data.model.Category
import com.ango.circle.core.data.model.User
import com.ango.circle.core.state.ErrorState
import com.ango.circle.core.state.State
import com.ango.circle.core.state.SuccessState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*

class UserInteractorImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val IO: CoroutineDispatcher = Dispatchers.IO
) : IUserInteractor {

    override suspend fun signUpUser(
        email: String,
        password: String,
        onCompleteListener: (State) -> Unit
    ) {
        withContext(IO) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    onCompleteListener(SuccessState("Successful"))
                } else if (it.isCanceled) {
                    onCompleteListener(ErrorState(message = "SignUp is cancelled!"))
                } else {
                    onCompleteListener(ErrorState(message = it.exception?.message))
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
                    onCompleteListener(ErrorState(message = "SignIn is cancelled!"))
                } else {
                    onCompleteListener(ErrorState(message = it.exception?.message))
                }
            }
        }
    }

    override suspend fun signOutUser() {
        withContext(IO) {
            firebaseAuth.signOut()
        }
    }

    override suspend fun insertUser(user: User) {
        TODO("insert user into user collection")
    }

    override suspend fun getCategories(onCompleteListener: (State) -> Unit) {
//        val data = arrayListOf(
//            Category("abc", "Football", "Football is a game consist of 22 players", ""),
//            Category("efg", "Basktball", "Basktball is a game consist of 10 players", ""),
//            Category("hij", "Rugby", "Rugby is a game consist of 20 players", ""),
//            Category("klm", "Squash", "Squash is a game consist of 2 players", ""),
//        )
        withContext(IO) {
            val categoryList = mutableListOf<Category>()
            firebaseFirestore.collection(categoryCollection)
                .get()
                .addOnSuccessListener {
                    it.documents.forEach { documentSnapshot ->
                        val category = documentSnapshot.toObject(Category::class.java)
                        category?.let { it ->
                            categoryList.add(it)
                        }
                    }
                    onCompleteListener(SuccessState(data = categoryList))

                }
                .addOnFailureListener {
                    onCompleteListener(ErrorState(errorCode = it.message,message = "failed to fetch cirlce categories"))
                }
        }

//        TODO("get categories from categories collection")
    }

}