package com.mafraq.data.repository.hardware

import com.mafraq.data.source.location.LocationDataSource
import com.mafraq.data.source.location.LocationDataSourceImpl
import javax.inject.Inject


class HardwareRepositoryImpl @Inject constructor(
    locationDataSource: LocationDataSourceImpl
): HardwareRepository, LocationDataSource by locationDataSource{

}
