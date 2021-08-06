package com.ango.circle.core.interactors.user

import android.graphics.Bitmap
import com.ango.circle.core.data.model.Category
import com.ango.circle.core.data.model.User
import com.ango.circle.core.state.State
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.UploadTask

interface IUserInteractor {
    suspend fun signUpUser(email: String, password: String, onCompleteListener: (State) -> Unit)
    suspend fun signInUser(email: String,password: String,onCompleteListener: (State) -> Unit)
    suspend fun signOutUser()

    suspend fun insertUser(user: User,onCompleteListener:(State)->Unit)
    suspend fun getCategories(onCompleteListener:(State)->Unit)

    suspend fun uploadUserPicture(imageName:String,bitmap: Bitmap,onCompleteListener: (State) -> Unit,onProgress:(Int)->Unit):UploadTask
    suspend fun downloadUserPicture(path:String, onCompleteListener:(State)->Unit,onProgress: (Int) -> Unit)
}