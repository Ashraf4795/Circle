package com.ango.circle.views.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.ango.circle.R
import com.ango.circle.databinding.ActivityMainBinding
import com.ango.circle.views.signup.signup_screen.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val signUpViewModel: SignUpViewModel by viewModel()
    private lateinit var navHostController: NavHostFragment
    private lateinit var mainActivityBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        setUpNavigationController()
        initNavControllerListener()

    }

    private fun initNavControllerListener() {
        navHostController.navController.addOnDestinationChangedListener{_,destination,_ ->
            when(destination.id) {
                R.id.login_flow ->{
//                    hideBottomNavigation()
                }
                else -> {
//                    showBottomNavigation()
                }
            }

        }
    }

//    private fun showBottomNavigation() {
//        mainActivityBinding.bottomNavigation.visibility = View.VISIBLE
//    }
//
//    private fun hideBottomNavigation() {
//        mainActivityBinding.bottomNavigation.visibility = View.GONE
//    }

    private fun setUpNavigationController() {
        navHostController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
}