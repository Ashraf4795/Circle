package com.ango.circle.core.data.model

import com.ango.circle.core.data.model.enums.Gender

data class User(
    private val userId:String,
    private val userName:String,
    private val userImgUrl:String ?= null,
    private val userGender: Gender,
    private val userInterests:List<Category>
)
