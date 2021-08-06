package com.ango.circle.core.di

import com.ango.circle.core.interactors.user.IUserInteractor
import com.ango.circle.core.interactors.user.UserInteractorImpl
import org.koin.dsl.module


val interactorModule = module{
    single<IUserInteractor>{UserInteractorImpl(get(),get(),get())}
}