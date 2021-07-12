package com.ango.circle.views.signup.signup_screen


sealed class InCompleteInput {
    data class NameComplete      (val message: String = "Please enter your name.") : InCompleteInput()
    data class EmailComplete     (val message: String = "Please enter your Email.") : InCompleteInput()
    data class PasswordComplete  (val message: String = "Please enter your Password.") : InCompleteInput()
    data class PasswordStrong    (val message: String = "Please enter your a password consist of at least 8 character and symbols.") : InCompleteInput()
    data class GenderComplete    (val message: String = "Please select your Gender.") : InCompleteInput()
    data class UserImageComplete (val message: String = "Please select your Picture.") : InCompleteInput()
    data class CategoriesComplete(val message: String = "Please select what are the categories that interests you.") : InCompleteInput()

}


