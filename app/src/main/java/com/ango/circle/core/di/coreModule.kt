package com.ango.circle.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module


val coreModule = module{
    single { provideFirebaseAuthInstnace() }
}

private fun provideFirebaseAuthInstnace(): FirebaseAuth {
    return Firebase.auth
}