package com.ango.circle.core.data.model

data class CirclePlace(
    val country:String,
    val city:String,
    val placeName:String,
    private val placeLocation:Pair<Double,Double>
) {
    fun getPlaceDisplayName():String{
        /* todo: check the length of city + country + separator, if > 25, return country shortcut */
        return "$city, $country"
    }

    fun getLongitude() = placeLocation.first
    fun getLatitude() = placeLocation.second
}
