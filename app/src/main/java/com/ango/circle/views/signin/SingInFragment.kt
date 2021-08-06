package com.ango.circle.views.signin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.ango.circle.core.state.ErrorState
import com.ango.circle.core.state.LoadingState
import com.ango.circle.core.state.SuccessState
import com.ango.circle.core.utils.showToast
import com.ango.circle.databinding.FragmentSignInBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SingInFragment : Fragment() {
    private val TAG = "SingInFragment"
    private lateinit var signInBinding: FragmentSignInBinding
    private val signInViewModel:SignInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signInBinding = FragmentSignInBinding.inflate(inflater,container,false)
        return signInBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSignInClickListener()
        initSignInLiveDataObservation()
    }

    private fun initSignInLiveDataObservation() {
        lifecycleScope.launchWhenResumed {
            signInViewModel.userSignInState.observe(viewLifecycleOwner){signInState ->
                when(signInState) {
                    is SuccessState<*> ->{
                        Log.d(TAG, "Success ")
                        //TODO: go to welcome user page
                        showToast(requireActivity(),"success", Toast.LENGTH_LONG)
                    }
                    is LoadingState<*>->{
                        Log.d(TAG, "Loading ")
                        showToast(requireActivity(),"Loading", Toast.LENGTH_LONG)
                    }
                    is ErrorState -> {
                        //TODO: show user error message
                        Log.d(TAG, signInState.toString() ?:"error message null")
                    }
                }
            }
        }
    }

    private fun initSignInClickListener() {
        signInBinding.loginBtnId.setOnClickListener{
            Log.d(TAG, "clicked ")
            val (email,password) = getUserInput()
            signInViewModel.signInUser(email,password)
        }
    }

    private fun getUserInput(): Pair<String,String>{
        val userEmail = signInBinding.signinEmailInputId.text.toString()
        val userPassword = signInBinding.signinPasswordInputId.text.toString()
        Log.d(TAG, "user input: \nemail:$userEmail\n password:$userPassword")
        return Pair(userEmail, userPassword)
    }
}