package com.ango.circle.core.di

import com.ango.circle.views.home.explore.ExploreViewModel
import com.ango.circle.views.signin.SignInViewModel
import com.ango.circle.views.signup.signup_screen.SignUpViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single{ SignUpViewModel(get()) }
    single{ SignInViewModel(get()) }
    single{ ExploreViewModel(get()) }
}