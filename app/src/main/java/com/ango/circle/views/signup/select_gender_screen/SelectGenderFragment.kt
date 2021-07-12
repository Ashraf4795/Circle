package com.ango.circle.views.signup.select_gender_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ango.circle.R
import com.ango.circle.databinding.FragmentSelectGenderBinding
import com.ango.circle.views.signup.signup_screen.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SelectGenderFragment : Fragment() {
    private val signupViewModel by sharedViewModel<SignUpViewModel>()
    private lateinit var selectGenderBinding:FragmentSelectGenderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        selectGenderBinding = FragmentSelectGenderBinding.inflate(inflater,container,false)
        return selectGenderBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("User","user is ${signupViewModel.user}")
    }

}