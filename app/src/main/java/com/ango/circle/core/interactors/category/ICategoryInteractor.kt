package com.ango.circle.core.interactors.category

import com.ango.circle.core.data.model.Category
import com.ango.circle.core.state.State

interface ICategoryInteractor {
    suspend fun getCategories(onCompleteListener:(State)->Unit)
}