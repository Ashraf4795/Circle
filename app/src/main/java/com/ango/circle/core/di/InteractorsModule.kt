package com.ango.circle.core.di

import com.ango.circle.core.interactors.category.CategoryInteractorImpl
import com.ango.circle.core.interactors.category.ICategoryInteractor
import com.ango.circle.core.interactors.circle.CircleInteractorImpl
import com.ango.circle.core.interactors.circle.ICircleInteractor
import com.ango.circle.core.interactors.user.IUserInteractor
import com.ango.circle.core.interactors.user.UserInteractorImpl
import org.koin.dsl.module


val interactorModule = module{
    single<IUserInteractor>{UserInteractorImpl(get())}
    single<ICircleInteractor>{CircleInteractorImpl(get())}
    single<ICategoryInteractor>{CategoryInteractorImpl(get())}
}