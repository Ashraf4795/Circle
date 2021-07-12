package com.ango.circle.views.signup.signup_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ango.circle.core.data.model.Category
import com.ango.circle.core.data.model.SignUpUser
import com.ango.circle.core.data.model.User
import com.ango.circle.core.data.model.enums.Gender
import com.ango.circle.core.repos.user.IUserRepository
import com.ango.circle.core.state.ErrorState
import com.ango.circle.core.state.State
import com.ango.circle.core.state.SuccessState
import com.ango.circle.core.utils.validateEmail
import com.ango.circle.core.utils.validatePassword
import com.ango.circle.core.utils.validateUserName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val userRepository: IUserRepository,
    private val IO: CoroutineDispatcher = Dispatchers.IO
) : ViewModel(){
    private val TAG = "SignUpViewModel"
    private lateinit var user: User

    private val _userSignUpState = MutableLiveData<State>()
    val userSignUpState:LiveData<State> = _userSignUpState

    private val _categoriesState = MutableLiveData<State>()
    val categoriesState = _categoriesState

    private val _signUpInputCompletion = MutableLiveData<InCompleteInput>()
    val signUpInputCompletion = _signUpInputCompletion

    private val signUpJobs = mutableListOf<Job>()

    fun signUpUser(signupUser: SignUpUser) {
        signUpJobs += viewModelScope.launch(IO) {
            userRepository.signupUser(signupUser.email,signupUser.password){
                when(it) {
                    is SuccessState<*> ->{
                        val userId = (it.data as String)
                        user = User(userId = userId,userName= signupUser.name,userEmail = signupUser.email)
                    }
                    is ErrorState ->{}
                }
                Log.d("viewModel","emit value $it")
            }
        }
    }

    fun getCategories() {
        signUpJobs += viewModelScope.launch(IO) {
            userRepository.getCategories{
                _categoriesState.postValue(it)
                Log.d("viewModel","emit value $it")
            }
        }
    }

    fun setUserInterests(listOfCategories:List<Category>) {
        user.userInterests = listOfCategories
    }
    fun setUserGender(gender: Gender) {
        user.userGender = gender
    }

    suspend fun insertUser() {
        signUpJobs += viewModelScope.launch(IO) {
            userRepository.insertUser(user) {
                _userSignUpState.value = it
                Log.d(TAG,"Signup state: $it")
            }
        }
    }

    fun test() = "created"

    override fun onCleared() {
        super.onCleared()
        clearJobs()
    }

    private fun clearJobs() {
        for(job in signUpJobs) {
            if(!job.isCancelled) {
                job.cancel()
            }
        }
    }

    fun validateUserSignupInput(signupUser: SignUpUser):Boolean {
        val isUserNameValid = validateUserName(signupUser.name)
        val isEmailValid =    validateEmail(signupUser.email)
        val isPasswordValid = validatePassword(signupUser.password)

        if(!isUserNameValid) {
            _signUpInputCompletion.value = InCompleteInput.NameComplete()
            return false
        }
        if(!isEmailValid) {
            _signUpInputCompletion.value = InCompleteInput.EmailComplete()
            return false
        }
        if(!isPasswordValid) {
            _signUpInputCompletion.value = InCompleteInput.PasswordComplete()
            return false
        }
        return true
    }

}