package com.ango.circle.core.data.model

data class Fee(
    var feeAmount: Float? = null,
    var has_discount: Boolean = false,
    var feeDiscount: Int? = null
)

