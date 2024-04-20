package com.mafraq.data.entities.map


data class Directions(
    val locationPoints: List<Location>,
    val distanceMeters: Int,
) {
    val zoomLevel: Double
        get() = distanceMeters.calculateZoomLevel()

    companion object {
        val empty = Directions(
            locationPoints = emptyList(),
            distanceMeters = 0,
        )
        private const val ZOOM_LEVEL_RATIO = 0.0015845
        private const val MAX_ZOOM_LEVEL = 11.5
    }

    private fun Int.calculateZoomLevel(): Double {
        var rate = 1.0
        var count = 0
        var value = this
        while (value != 0) {
            value /= 10
            if (count >= 4)
                rate *= 10
            count++
        }
        val result = (ZOOM_LEVEL_RATIO * this) / rate
        val level = if (result > MAX_ZOOM_LEVEL)
            MAX_ZOOM_LEVEL - 3.5
        else
            result
        return level
    }
}
