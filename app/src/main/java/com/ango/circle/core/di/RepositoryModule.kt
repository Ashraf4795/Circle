package com.ango.circle.core.di

import com.ango.circle.core.repos.category.CategoryRepositoryImpl
import com.ango.circle.core.repos.category.ICategoryRepository
import com.ango.circle.core.repos.circle.CircleRepositoryImp
import com.ango.circle.core.repos.circle.ICircleRepository
import com.ango.circle.core.repos.user.IUserRepository
import com.ango.circle.core.repos.user.UserRepositoryImpl
import org.koin.dsl.module


val repoModule = module{
    single <IUserRepository> {UserRepositoryImpl(get())}
    single <ICircleRepository> {CircleRepositoryImp(get())}
    single <ICategoryRepository> {CategoryRepositoryImpl(get())}
}