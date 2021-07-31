package com.ango.circle.views.signup.select_gender_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ango.circle.R
import com.ango.circle.core.data.model.enums.Gender
import com.ango.circle.core.state.PermissionState
import com.ango.circle.core.utils.PermissionManager
import com.ango.circle.core.utils.showToast
import com.ango.circle.databinding.FragmentSelectGenderBinding
import com.ango.circle.views.showDialog
import com.ango.circle.views.signup.signup_screen.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class SelectGenderFragment : Fragment() {
    private val signupViewModel by sharedViewModel<SignUpViewModel>()
    private var isMaleClicked = false
    private var isFemaleClicked = false
    private lateinit var selectGenderBinding: FragmentSelectGenderBinding
    private val cameraPermission = android.Manifest.permission.CAMERA

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
            } else {
                //Explain to the user that the feature is unavailable
            }
            println("activity permission grant result is: $isGranted")
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

    }


    private fun setSelectUserProfileImageListener() {
        selectGenderBinding.userImgId.setOnClickListener {
            chooseImage()
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
        selectGenderBinding.nextBtnId.setOnClickListener {
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

    private fun askForPermission(): PermissionState {
        val permissionState =
            PermissionManager.requestPermission(this.requireActivity(), cameraPermission)
        println("permission: $permissionState")
        return permissionState
    }

    private fun chooseImage() {
        when (askForPermission()) {
            PermissionState.GRANTED -> {
                performChooseImage()
            }
            PermissionState.DENIED -> {
                requestPermissionLauncher.launch(cameraPermission)
                //request the permission
            }
            PermissionState.EXPLAIN -> {
                println("explain")
                showDialog(this.requireActivity(),
                    onPositiveCallBack = {
                        requestPermissionLauncher.launch(cameraPermission)
                    },
                    onNegativeCallBack = {
                        showToast(this.requireActivity(),resources.getString(R.string.degrade_feature_message))
                    })
                //show UI to clarify the need for the request permission.
            }
        }
    }

    private fun performChooseImage() {
        println("performChooseImage")
        findNavController().navigate(R.id.action_selectGenderFragment_to_imageCaptureFragment)
    }

}