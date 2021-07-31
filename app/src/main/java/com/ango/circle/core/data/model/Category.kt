package com.ango.circle.core.data.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class Category(
    @DocumentId
    var categoryId:String?=null,

    @get:PropertyName("category_name")
    @set:PropertyName("category_name")
    var categoryName:String?=null,

    var description:String?=null,

    @set:PropertyName("category_img")
    @get:PropertyName("category_img")
    var categoryImg:String?=null,

    @set:PropertyName("is_selected")
    @get:PropertyName("is_selected")
    var is_selected:Boolean?=null
)
