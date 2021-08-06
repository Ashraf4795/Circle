package com.ango.circle.views.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ango.circle.R
import com.ango.circle.views.signup.signup_screen.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }
}