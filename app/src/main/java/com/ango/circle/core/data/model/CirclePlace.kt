package com.ango.circle.core.data.model

data class CirclePlace(
    var country: String? = null,
    var city: String? = null,
    var placeName: String? = null,
    private var placeLocation: Pair<Double, Double>? = null
) {
    fun getPlaceDisplayName(): String {
        /* todo: check the length of city + country + separator, if > 25, return country shortcut */
        return "$city, $country"
    }

    fun getLongitude() = placeLocation?.first
    fun getLatitude() = placeLocation?.second
}
