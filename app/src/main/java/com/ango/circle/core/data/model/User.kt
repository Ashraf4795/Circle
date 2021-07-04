package com.ango.circle.core.data.model

import com.ango.circle.core.data.model.enums.Gender

data class User(
    val userId:String,
    val userName:String,
    val userImgUrl:String ?= null,
    val userGender: Gender,
    val userInterests:List<Category>
)
