package com.ango.circle.core.data.model

import com.ango.circle.core.data.model.enums.Gender
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class Circle(
    @DocumentId
    var circleId: String? = null,

    @get:PropertyName("circleTitle")
    var circleTitle: String? = null,

    @get:PropertyName("circleImgUrl")
    var circleImgUrl: String? = null,

    @get:PropertyName("description")
    var description: String? = null,

    @get:PropertyName("circle_category")
    var circleCategory: Category? = null,

    @get:PropertyName("category_id")
    var categoryId: String?=null,

    @get:PropertyName("participants")
    var participants: List<User>?=null,

    @get:PropertyName("allowed_gender")
    var allowedGender: Gender?=null,

    @get:PropertyName("place")
    var place: CirclePlace?=null,

    @get:PropertyName("created")
    var created: Timestamp?=null,

    @get:PropertyName("fees")
    var fees: Fee?=null
)
