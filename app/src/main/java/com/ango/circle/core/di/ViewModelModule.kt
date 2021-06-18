package com.ango.circle.core.di

import com.ango.circle.views.signup.SignUpViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single{SignUpViewModel(get())}
}