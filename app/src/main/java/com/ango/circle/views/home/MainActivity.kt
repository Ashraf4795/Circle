package com.ango.circle.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ango.circle.R
import com.ango.circle.views.signup.signup_screen.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

//test
class MainActivity : AppCompatActivity() {
    private val signUpViewModel: SignUpViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}