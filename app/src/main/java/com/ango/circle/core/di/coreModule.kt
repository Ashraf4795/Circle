package com.ango.circle.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import org.koin.dsl.module


val coreModule = module{
    single { provideFirebaseAuthInstnace() }
    single { provideFirebaseFirestoreInstance()}
}

private fun provideFirebaseAuthInstnace(): FirebaseAuth {
    return Firebase.auth
}

private fun provideFirebaseFirestoreInstance():FirebaseFirestore{
    return Firebase.firestore
}

private fun provideFirebaseStorageInstance() : FirebaseStorage = FirebaseStorage.getInstance();