package com.ango.circle.views.signup.signup_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ango.circle.R
import com.ango.circle.core.data.model.SignUpUser
import com.ango.circle.core.state.ErrorState
import com.ango.circle.core.state.LoadingState
import com.ango.circle.core.state.SuccessState
import com.ango.circle.core.utils.*
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
        initSignUpObserver()
        initUserInputCompletionObserver()

    }

    private fun initUserInputCompletionObserver() {
        signUpViewModel.signUpInputCompletion.observe(viewLifecycleOwner){
            //todo handle error text under the specified input field based on @it type.
            println(it)
        }
    }

    private fun initSignUpObserver() {
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
            val signupUser = getUserInput()
            signUpViewModel.validateUserSignupInput(signupUser).let { inputValid ->
                if(inputValid) {
                    signUpViewModel.signUpUser(signupUser)
                }
            }
        }
    }





    private fun getUserInput(): SignUpUser {
        val userName = signupBinding.nameTextInputId.text.toString()
        val userEmail = signupBinding.emailTextInputId.text.toString()
        val userPassword = signupBinding.passwordTextInputId.text.toString()
        Log.d(TAG, "user input: \n name:$userName\n email:$userEmail\n password:$userPassword")
        return SignUpUser(userName,userEmail,userPassword)
    }



}

