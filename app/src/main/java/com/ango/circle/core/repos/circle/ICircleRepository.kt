package com.ango.circle.core.repos.circle

import com.ango.circle.core.state.State

interface ICircleRepository {
    suspend fun getCirclesList(onCompleteListener: (State) -> Unit)
    suspend fun getCirclesListByName(
        query: String,
        onCompleteListener: (State) -> Unit
    )
}