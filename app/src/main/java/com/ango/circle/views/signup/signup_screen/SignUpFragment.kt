package com.ango.circle.views.signup.signup_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ango.circle.R
import com.ango.circle.core.state.ErrorState
import com.ango.circle.core.state.LoadingState
import com.ango.circle.core.state.SuccessState
import com.ango.circle.core.utils.showToast
import com.ango.circle.databinding.FragmentSignUpBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SignUpFragment : Fragment() {
    val TAG = "SignUpFragment"
    private val signUpViewModel by sharedViewModel<SignUpViewModel>()
    private lateinit var signupBinding: FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        signupBinding = FragmentSignUpBinding.inflate(inflater, container,false)
        return signupBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("test", signUpViewModel.test())
        initSignUpClickListener()
        initviewModelObservers()
    }

    private fun initviewModelObservers() {
        signUpViewModel.userSignUpState.observe(viewLifecycleOwner){signUpState ->
            signUpState?.let{
                when(it) {
                    is SuccessState<*> ->{
                        //TODO: go to welcome user page
                        showToast(requireActivity(),this.resources.getString(R.string.successful_signup_msg), Toast.LENGTH_LONG)
                    }
                    is LoadingState<*>->{
                        showToast(requireActivity(),"Loading", Toast.LENGTH_LONG)
                    }
                    is ErrorState -> {
                        Log.d(TAG, it.message ?:"error message null")
                    }
                }
            }?: showToast(requireActivity(),"null",Toast.LENGTH_LONG)
        }
    }



    private fun initSignUpClickListener() {
        signupBinding.signupBtnId.setOnClickListener{
            val (name,email,password) = getUserInput()
            if(isValid(name,email,password)) {
                Log.d(TAG, "user input valid")
                signUpViewModel.signUpUser(name,email,password)
            } else{
                Log.d(TAG, "user input not valid")
            }
        }
    }



    private fun isValid(name:String,email:String,password:String ):Boolean{
        val isNameValid = true
        val isEmailValid = true
        val isPasswordValid = true
        return true
    }

    private fun getUserInput(): SignUpUser {
        val userName = signupBinding.nameTextInputId.text.toString()
        val userEmail = signupBinding.emailTextInputId.text.toString()
        val userPassword = signupBinding.passwordTextInputId.text.toString()
        Log.d(TAG, "user input: \n name:$userName\n email:$userEmail\n password:$userPassword")
        return SignUpUser(userName,userEmail,userPassword)
    }

    private data class SignUpUser(val name:String, val email:String, val password:String)

}

