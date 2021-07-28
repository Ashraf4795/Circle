package com.ango.circle.views.signup.select_gender_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ango.circle.R
import com.ango.circle.core.data.model.enums.Gender
import com.ango.circle.core.utils.showToast
import com.ango.circle.databinding.FragmentSelectGenderBinding
import com.ango.circle.views.signup.signup_screen.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.math.sign

class SelectGenderFragment : Fragment() {
    private val signupViewModel by sharedViewModel<SignUpViewModel>()
    private var isMaleClicked = false
    private var isFemaleClicked = false
    private lateinit var selectGenderBinding: FragmentSelectGenderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        selectGenderBinding = FragmentSelectGenderBinding.inflate(inflater, container, false)
        return selectGenderBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateGenderButtons()
        setGenderButtonsListener()
        setSelectUserProfileImageListener()
        //Log.d("User","user is ${signupViewModel.user}")
    }

    private fun setSelectUserProfileImageListener() {
        selectGenderBinding.userImgId.setOnClickListener{
            showToast(requireContext(),"user profile image")
        }
    }

    private fun setGenderButtonsListener() {
        selectGenderBinding.maleBtnId.setOnClickListener {
            signupViewModel.user.userGender = Gender.MALE
            updateGenderButtons()

        }
        selectGenderBinding.femaleBtnId.setOnClickListener {
            signupViewModel.user.userGender = Gender.FEMALE
            updateGenderButtons()
        }
        selectGenderBinding.nextBtnId.setOnClickListener{
            findNavController().navigate(R.id.action_selectGenderFragment_to_categoryFragment2)
        }
    }

    private fun updateGenderButtons() {
        setGenderFlagsValue()
        with(selectGenderBinding) {
            if (isMaleClicked) {
                updateButtonClickState(maleBtnId, R.drawable.gender_button_selected_shap)
            } else {
                updateButtonClickState(maleBtnId, R.drawable.gender_button_shape)
            }
            if (isFemaleClicked) {
                updateButtonClickState(femaleBtnId, R.drawable.gender_button_selected_shap)
            } else {
                updateButtonClickState(femaleBtnId, R.drawable.gender_button_shape)
            }
        }
    }

    private fun setGenderFlagsValue() {
        signupViewModel.user.let { user ->
            val isMale = signupViewModel.user.userGender == Gender.MALE
            isMaleClicked = isMale
            isFemaleClicked = !isMale
        }


    }

    private fun updateButtonClickState(
        layout: LinearLayout,
        layoutBg: Int,
    ) {
        layout.setBackground(ContextCompat.getDrawable(requireContext(), layoutBg))
    }

}