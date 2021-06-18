package com.ango.circle

import android.app.Application
import com.ango.circle.core.di.coreModule
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject

class App : Application() {

    val  firebaseAuth:FirebaseAuth by inject(FirebaseAuth::class.java)

    override fun onCreate() {
        super.onCreate()

        startKoin{
            modules(
                coreModule,
            )
        }
    }

}