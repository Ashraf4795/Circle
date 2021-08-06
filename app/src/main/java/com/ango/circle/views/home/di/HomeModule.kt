package com.ango.circle.views.home.di

import com.ango.circle.core.interactors.home.HomeInteractor
import com.ango.circle.core.interactors.home.IHomeInteractor
import com.ango.circle.views.home.explore.ExploreViewModel
import org.koin.dsl.module

val homeModule = module {
    single { ExploreViewModel(get()) }
    single<IHomeInteractor>{ HomeInteractor(get())}
}