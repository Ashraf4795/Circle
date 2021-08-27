package com.ango.circle.core.repos.category

import com.ango.circle.core.interactors.category.ICategoryInteractor
import com.ango.circle.core.state.State

class CategoryRepositoryImpl(val categoryInteractorImpl: ICategoryInteractor) :
    ICategoryRepository {
    override suspend fun getCategoriesList(onCompleteListener: (State) -> Unit) {
        categoryInteractorImpl.getCategories(onCompleteListener)
    }
}