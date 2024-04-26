package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.altaie.prettycode.core.exceptions.HttpException

class HttpExceptionMapper : Mapper<retrofit2.HttpException, HttpException> {
    override fun map(from: retrofit2.HttpException): HttpException {
        return HttpException(
            message = from.response()?.errorBody()?.string(),
            code = from.code()
        )
    }
}