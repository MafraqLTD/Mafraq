package com.mafraq.data.repository.hardware

import com.mafraq.data.local.location.LocationDataSource
import com.mafraq.data.local.location.LocationDataSourceImpl
import javax.inject.Inject


class HardwareRepositoryImpl @Inject constructor(
    locationDataSource: LocationDataSourceImpl
): HardwareRepository, LocationDataSource by locationDataSource{

}
