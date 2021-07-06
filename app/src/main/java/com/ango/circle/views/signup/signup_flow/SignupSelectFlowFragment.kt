package com.ango.circle.views.signup.signup_flow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.ango.circle.R
import com.ango.circle.databinding.FragmentSignupSelectFlowBinding
import com.ango.circle.views.signup.signup_screen.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SignupSelectFlowFragment : Fragment() {
    private lateinit var signUpFlowViewPager: ViewPager2
    private lateinit var signupSelectFlowBinding: FragmentSignupSelectFlowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        signupSelectFlowBinding = FragmentSignupSelectFlowBinding.inflate(inflater,container,false)
        return signupSelectFlowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager () {
        signUpFlowViewPager = signupSelectFlowBinding.signupSelectViewPagerId
        signUpFlowViewPager.adapter = SignUpSelectFlowAdapter(requireActivity())

    }
}