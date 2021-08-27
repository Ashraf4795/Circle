package com.ango.circle.core.interactors.user

import android.graphics.Bitmap
import com.ango.circle.core.categoryCollection
import com.ango.circle.core.data.model.Category
import com.ango.circle.core.data.model.User
import com.ango.circle.core.interactors.FirebaseInteractor
import com.ango.circle.core.state.ErrorState
import com.ango.circle.core.state.State
import com.ango.circle.core.state.SuccessState
import com.ango.circle.core.userCollection
import com.ango.circle.core.userPicturesBucket
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.*
import java.io.ByteArrayOutputStream

class UserInteractorImpl(
    private val firebaseInteractor: FirebaseInteractor,
) : IUserInteractor by firebaseInteractor