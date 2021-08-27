package com.ango.circle.core.interactors

import android.graphics.Bitmap
import com.ango.circle.core.categoryCollection
import com.ango.circle.core.circlesCollection
import com.ango.circle.core.data.model.Category
import com.ango.circle.core.data.model.Circle
import com.ango.circle.core.data.model.User
import com.ango.circle.core.data.model.enums.Gender
import com.ango.circle.core.interactors.category.ICategoryInteractor
import com.ango.circle.core.interactors.home.IHomeInteractor
import com.ango.circle.core.interactors.user.IUserInteractor
import com.ango.circle.core.state.ErrorState
import com.ango.circle.core.state.State
import com.ango.circle.core.state.SuccessState
import com.ango.circle.core.userCollection
import com.ango.circle.core.userPicturesBucket
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.util.*

class FirebaseInteractor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage,
    private val IO: CoroutineDispatcher = Dispatchers.IO
) : IUserInteractor, ICategoryInteractor, IHomeInteractor {


    override suspend fun signUpUser(
        email: String,
        password: String,
        onCompleteListener: (State) -> Unit
    ) {
        withContext(IO) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    onCompleteListener(SuccessState(it.result?.user?.uid ?: "user null"))
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

    override suspend fun signOutUser() {
        firebaseAuth.signOut()
    }

    override suspend fun insertUser(user: User, onCompleteListener: (State) -> Unit) {
        firebaseFirestore.collection(userCollection)
            .document(user.userId)
            .set(user)
            .addOnSuccessListener {
                onCompleteListener(SuccessState(user))
            }
            .addOnFailureListener {
                onCompleteListener(ErrorState(message = it.message))
            }
            .addOnCanceledListener {
                onCompleteListener(ErrorState())
            }
    }

    override suspend fun getCategories(onCompleteListener: (State) -> Unit) {
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
                onCompleteListener(
                    ErrorState(
                        errorCode = it.message,
                        message = "failed to fetch cirlce categories"
                    )
                )
            }
    }

    override suspend fun uploadUserPicture(
        imageName: String,
        bitmap: Bitmap,
        onCompleteListener: (State) -> Unit,
        onProgress: (Int) -> Unit
    ): UploadTask {
        val byteArrayOutPutStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutPutStream)
        val pictureData = byteArrayOutPutStream.toByteArray()
        val pictureRef = firebaseStorage.reference.child("$userPicturesBucket/$imageName")
        val uploadTask = pictureRef.putBytes(pictureData)
        uploadTask.addOnCompleteListener {
            onCompleteListener.invoke(SuccessState(data = it.result))
        }.addOnProgressListener {
            val progress = (100.0 * it.bytesTransferred) / it.totalByteCount
            onProgress.invoke(progress.toInt())
        }
        return uploadTask
    }

    override suspend fun downloadUserPicture(
        path: String,
        onCompleteListener: (State) -> Unit,
        onProgress: (Int) -> Unit
    ) {
        firebaseStorage.reference.child(path).downloadUrl.addOnSuccessListener {
            onCompleteListener.invoke(SuccessState(data = it))
        }.addOnFailureListener {
            onCompleteListener(ErrorState(message = it.message))
        }
    }

    override suspend fun getAllCircles(onComplete: (State) -> Unit, onFailure: (State) -> Unit) {
        firebaseFirestore.collection(circlesCollection)
            .get()
            .addOnSuccessListener {querySnapshot->
                val listOfCircles = mutableListOf<Circle>()
                querySnapshot.documents.forEach {documentSnapshot->
                    documentSnapshot.toObject(Circle::class.java)?.let {circle->
                        listOfCircles.add(circle)
                    }

                }
                onComplete(SuccessState<List<Circle>?>(data = listOfCircles))


            }
            .addOnFailureListener {
                onFailure(ErrorState(message = it.message))
            }
    }

    override suspend fun getCirclesOf(
        categoryTag: String,
        onComplete: (State) -> Unit,
        onFailure: (State) -> Unit
    ) {
        firebaseFirestore.collection(circlesCollection)
            .whereEqualTo("circle_category",categoryTag)
            .get()
            .addOnSuccessListener {querySnapshot->
                val listOfCircles = mutableListOf<Circle>()
                querySnapshot.documents.forEach {documentSnapshot->
                    documentSnapshot.toObject(Circle::class.java)?.let {circle->
                        listOfCircles.add(circle)
                    }

                }
                onComplete(SuccessState<List<Circle>?>(data = listOfCircles))


            }
            .addOnFailureListener {
                onFailure(ErrorState(message = it.message))
            }
    }

    override suspend fun getCircleById(
        circleId: String,
        onComplete: (State) -> Unit,
        onFailure: (State) -> Unit
    ) {
        firebaseFirestore.collection(circlesCollection)
            .get()
            .addOnSuccessListener {querySnapshot->
                val listOfCircles = mutableListOf<Circle>()
                querySnapshot.documents.forEach {documentSnapshot->
                    documentSnapshot.toObject(Circle::class.java)?.let {circle->
                        if(circle.circleId == circleId)
                             listOfCircles.add(circle)
                    }

                }
                onComplete(SuccessState<List<Circle>?>(data = listOfCircles))


            }
            .addOnFailureListener {
                onFailure(ErrorState(message = it.message))
            }
    }

    override suspend fun getCirclesByFilterOptions(
        filteration: Map<String, Any>,
        onComplete: (State) -> Unit,
        onFailure: (State) -> Unit
    ) {
        firebaseFirestore.collection(circlesCollection)
            .get()
            .addOnSuccessListener {querySnapshot->
                val listOfCircles = mutableListOf<Circle>()
                querySnapshot.documents.forEach {documentSnapshot->
                    documentSnapshot.toObject(Circle::class.java)?.let { circle ->
                        listOfCircles.add(circle)
                    }

                }
                onComplete(SuccessState<List<Circle>?>(data = listOfCircles))


            }
            .addOnFailureListener {
                onFailure(ErrorState(message = it.message))
            }
    }

    override suspend fun getUserMessages(onComplete: (State) -> Unit, onFailure: (State) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserNotifications(
        onComplete: (State) -> Unit,
        onFailure: (State) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularCirlces(
        onComplete: (State) -> Unit,
        onFailure: (State) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getDiscoverPlaces(
        onComplete: (State) -> Unit,
        onFailure: (State) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    private fun returnFirebaseQuery(collectionReference:CollectionReference,queryMap:Map<String,Any>) {
    }

}