package com.mafraq.presentation.utils.extensions

import com.google.android.gms.maps.model.LatLng
import com.mafraq.data.entities.map.Location


fun Location.toLatLng() = LatLng(latitude, longitude)
