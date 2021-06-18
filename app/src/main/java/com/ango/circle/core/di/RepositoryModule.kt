package com.ango.circle.core.di

import com.ango.circle.core.repos.user.IUserRepository
import com.ango.circle.core.repos.user.UserRepositoryImpl
import org.koin.dsl.module


val repoModule = module{
    single <IUserRepository> {UserRepositoryImpl(get())}
}