package com.ango.circle.views.signin

import android.util.Log
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

class SignInViewModel(
    private val userRepository: IUserRepository,
    private val IO: CoroutineDispatcher = Dispatchers.IO
) : ViewModel(){

    private val _userSignInState = MutableLiveData<State>()
    val userSignInState:LiveData<State> = _userSignInState
    private val signInJobs = mutableListOf<Job>()

    fun signInUser(email:String,password:String) {
        signInJobs += viewModelScope.launch(IO) {
            userRepository.signInUser(email,password){
                _userSignInState.value = it
                Log.d("viewModel","emit value $it")
            }
        }
    }

    fun test() = "created"

    override fun onCleared() {
        super.onCleared()
        clearJobs()
    }

    private fun clearJobs() {
        for(job in signInJobs) {
            if(!job.isCancelled) {
                job.cancel()
            }
        }
    }
}