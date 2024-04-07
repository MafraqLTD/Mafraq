package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.mafraq.data.entities.ApiResponse
import com.mafraq.data.remote.models.ApiResponseDto
import javax.inject.Inject


class ApiResponseFromDtoMapper @Inject constructor(
    private val driverFromRemoteMapper: DriverFromRemoteMapper
) : Mapper<ApiResponseDto, ApiResponse> {

    override fun map(from: ApiResponseDto) = from.run {
        ApiResponse(
            data = data.let(driverFromRemoteMapper::mapList),
            single = single?.let(driverFromRemoteMapper::map)
        )
    }
}
