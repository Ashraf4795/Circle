package com.ango.circle.core.state

sealed class State


data class SuccessState<T>(val data:T, val status:Status = Status.SUCCESS, val errorCode:String? = null, val message:String? = null):State()
data class LoadingState<T>(val data:T? = null, val status:Status = Status.LOADING, val errorCode:String? = null, val message:String? = null):State()
data class ErrorState<T>(val data:T? = null, val status:Status = Status.ERROR, val errorCode:String? = null, val message:String? = null):State()

