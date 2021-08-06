package com.ango.circle.core.data.model

data class Fee(
    val feeAmount:Float,
    val has_discount:Boolean = false,
    val feeDiscount:Int
)
