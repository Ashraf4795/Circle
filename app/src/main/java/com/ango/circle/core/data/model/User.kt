package com.ango.circle.core.data.model

import com.ango.circle.core.data.model.enums.Gender

data class User(
    val userId:String,
    var userName:String,
    val userEmail:String,
    var userImgUrl:String ?= null,
    var userGender: Gender ?= null,
    var userInterests:List<Category> ?=null
) {

    fun isUserCreationComplete():Boolean {
        return (userName != null && userImgUrl != null && userGender != null && userInterests != null)
    }
}


