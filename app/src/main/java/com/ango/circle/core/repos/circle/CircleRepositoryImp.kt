package com.ango.circle.core.repos.circle

import com.ango.circle.core.interactors.circle.ICircleInteractor
import com.ango.circle.core.state.State

class CircleRepositoryImp(private val circleInteractorImpl: ICircleInteractor) : ICircleRepository {
    override suspend fun getCirclesList(onCompleteListener: (State) -> Unit) {
        circleInteractorImpl.getCircles(onCompleteListener)
    }

    override suspend fun getCirclesListByName(
        query: String,
        onCompleteListener: (State) -> Unit
    ) {
        circleInteractorImpl.getCirclesByName(query,onCompleteListener)
    }
}