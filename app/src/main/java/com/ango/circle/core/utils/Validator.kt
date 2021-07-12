package com.ango.circle.core.utils

import android.util.Patterns


fun validateEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
fun validatePassword(password: String): Boolean {
    //todo: make validation more complex
    //source: https://java2blog.com/validate-password-java/
    return password.length > 7
}

fun validateUserName(userName: String): Boolean {
    //check if name is not empty and has not white spaces.
    return userName.isNotBlank()
}



