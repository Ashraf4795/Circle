package com.ango.circle.core.repos.user

import android.graphics.Bitmap
import com.ango.circle.core.data.model.User
import com.ango.circle.core.interactors.user.IUserInteractor
import com.ango.circle.core.state.State
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.UploadTask

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

    override suspend fun insertUser(user: User, onCompleteListener: (State) -> Unit) {
        userInteractorImpl.insertUser(user,onCompleteListener)
    }

    override suspend fun getCategories(onCompleteListener: (State) -> Unit) {
        userInteractorImpl.getCategories(onCompleteListener)
    }

    override suspend fun uploadUserPicture(
        imageName: String,
        bitmap: Bitmap,
        onCompleteListener: (State) -> Unit,
        onProgress: (Int) -> Unit
    ): UploadTask {
        return userInteractorImpl.uploadUserPicture(imageName,bitmap,onCompleteListener,onProgress)
    }

    override suspend fun downloadUserPicture(
        path: String,
        onCompleteListener: (State) -> Unit,
        onProgress: (Int) -> Unit
    ) {
        userInteractorImpl.downloadUserPicture(path,onCompleteListener,onProgress)
    }

}