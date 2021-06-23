package com.ango.circle.core.data.model

import com.ango.circle.core.data.model.enums.Gender
import java.sql.Timestamp

data class Circle(
    val circleId:String,
    val circleTitle:String,
    val circleImgUrl:String,
    val description:String,
    val circleCategory:Category,
    val categoryId:String,
    val participants:List<User>,
    val allowedGender: Gender,
    val place:CirclePlace,
    //todo: change this property to firebase.firestore.FieldValue.serverTimestamp()
    val created:String,
    val fees:Fee
)
