package com.ango.circle.core.interactors.home

import com.ango.circle.core.state.State

interface IHomeInteractor {

    suspend fun getAllCircles(onComplete:(State)->Unit,onFailure:(State)->Unit)
    suspend fun getCirclesOf(categoryTag:String, onComplete:(State)->Unit,onFailure:(State)->Unit)
    suspend fun getCircleById(circleId:String,onComplete:(State)->Unit,onFailure:(State)->Unit)
    suspend fun getCirclesByFilterOptions(filteration:Map<String,Any>, onComplete:(State)->Unit,onFailure:(State)->Unit)
    suspend fun getUserMessages(onComplete:(State)->Unit,onFailure:(State)->Unit)
    suspend fun getUserNotifications(onComplete:(State)->Unit,onFailure:(State)->Unit)
    suspend fun getPopularCirlces(onComplete:(State)->Unit,onFailure:(State)->Unit)
    suspend fun getDiscoverPlaces(onComplete:(State)->Unit,onFailure:(State)->Unit)

}