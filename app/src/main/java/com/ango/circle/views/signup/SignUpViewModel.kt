package com.ango.circle.views.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ango.circle.core.repos.user.IUserRepository
import com.ango.circle.core.state.State
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val userRepository: IUserRepository,
    private val IO: CoroutineDispatcher = Dispatchers.IO
) : ViewModel(){

    private val _userSignUpState = MutableLiveData<State>()
    val userSignUpState:LiveData<State> = _userSignUpState
    private lateinit var signUpJobs: MutableList<Job>

    fun signUpUser(email:String,password:String) {
        signUpJobs += viewModelScope.launch(IO) {
            userRepository.createUser(email,password){
                _userSignUpState.value = it
            }
        }
    }


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