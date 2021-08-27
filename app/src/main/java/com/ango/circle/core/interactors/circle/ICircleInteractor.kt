package com.ango.circle.core.interactors.circle

import com.ango.circle.core.state.State

interface ICircleInteractor {
    suspend fun getCircles(onCompleteListener: (State) -> Unit)
    suspend fun getCirclesByName(
        query: String,
        categoryId: String,
        onCompleteListener: (State) -> Unit
    )
}