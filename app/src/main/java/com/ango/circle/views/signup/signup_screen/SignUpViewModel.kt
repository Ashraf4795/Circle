package com.ango.circle.views.signup.signup_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ango.circle.core.data.model.Category
import com.ango.circle.core.data.model.User
import com.ango.circle.core.data.model.enums.Gender
import com.ango.circle.core.repos.user.IUserRepository
import com.ango.circle.core.state.ErrorState
import com.ango.circle.core.state.State
import com.ango.circle.core.state.SuccessState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

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

    private val signUpJobs = mutableListOf<Job>()

    fun signUpUser(name:String,email:String,password:String) {
        signUpJobs += viewModelScope.launch(IO) {
            userRepository.signupUser(email,password){
                when(it) {
                    is SuccessState<*> ->{
                        val userId = (it.data as String)
                        user = User(userId = userId,userName= name,userEmail = email)
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

}