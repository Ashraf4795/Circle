package com.ango.circle

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class App : Application() {

    lateinit var  firebaseAuth:FirebaseAuth
    private set

    override fun onCreate() {
        super.onCreate()
        firebaseAuth = Firebase.auth
    }

}