package com.ango.circle.core.repos.category

import com.ango.circle.core.state.State

interface ICategoryRepository {
    suspend fun getCategoriesList(onCompleteListener: (State) -> Unit)
}